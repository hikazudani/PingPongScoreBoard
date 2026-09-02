# PingPongScoreBoard

## Sobre o Projeto
Este é um aplicativo de placar de ping-pong desenvolvido para compreender na prática como o estado da interface se comporta durante a reconstrução da tela no Android (com Jetpack Compose). O projeto evolui a manutenção de estado através de quatro abordagens: `remember`, `ViewModel + mutableStateOf`, `ViewModel + StateFlow` e `ViewModel + SavedStateHandle`.

## Como Testar
Para preencher a tabela de resultados, cada abordagem deve ser submetida a dois cenários:
1. **Rotação de tela:** Marque alguns pontos e gire o emulador ou dispositivo físico.
2. **Morte do processo:** Marque pontos, coloque o app em segundo plano e mate o processo. Isso pode ser feito de duas formas:
    * Ativando a opção **"Não manter atividades"** nas *Opções do desenvolvedor* e trocando de app;
    * Ou via terminal usando o ADB: `adb shell am kill br.edu.ifsp.scl.sc3038432.pingpongscoreboard`

---

## Tabela de Testes

| Abordagem                    | Rotação de Tela | Morte do Processo |
|------------------------------|-----------------|-------------------|
| remember                     | Não sobrevive   | Não sobrevive     |
| ViewModel + mutableStateOf   | Sobrevive       | Não sobrevive     |
| ViewModel + StateFlow        | Sobrevive       | Não sobrevive     |
| ViewModel + SavedStateHandle | Sobrevive       | Sobrevive         |

## Perguntas

*1. Por que o ViewModel sozinho (etapas 2 e 3) não é suficiente para sobreviver à morte do processo, mesmo sobrevivendo à rotação de tela?*

R: Na rotação o processo do app não morre. A Activity é destruída e criada de novo, mas o Android 
guarda o ViewModel e passa ele para a nova Activity, então os dados continuam na memória.

Já quando o sistema mata o processo, a memória toda é liberada e o ViewModel vai junto, sobrando o
Bundle que a Activity mandou pro sistema no onSaveInstanceState, que fica guardado fora do app. 
O ViewModel sozinho não escreve nada nesse Bundle, então não tem como recuperar os dados. 
Para isso é necessário o SavedStateHandle, por exemplo.


*2. Qual a diferença prática entre usar mutableStateOf e StateFlow dentro do ViewModel nesta aplicação? Em algum momento essa diferença foi perceptível nos testes?*

R: O mutableStateOf pertence à API do Compose e o StateFlow às corrotinas do Kotlin. 
Para o usuário não há distinção, e nos testes as duas etapas se comportaram do mesmo modo.

A diferença está na arquitetura. Com mutableStateOf o estado fica dentro do sistema de snapshots do 
Compose, então basta o composable ler a propriedade para ser recomposto quando o valor muda. 
Com StateFlow o estado vira um fluxo observável, independente da camada de interface, e a tela 
precisa coletar esse fluxo com collectAsState. Nesta aplicação isso não trouxe ganho.

*3. Se este placar precisasse ser salvo permanentemente (mesmo após o usuário fechar o app e abrir dias depois), qual das quatro abordagens ainda seria insuficiente, e o que seria necessário adicionar?*

R: Nenhuma das quatro atende, nem a última. O SavedStateHandle vale enquanto a tarefa existe: se o usuário fecha o app pelas recentes ou reinicia o aparelho, o Bundle deixa de existir, porque ele fica na memória do sistema e não no armazenamento do dispositivo.

Seria preciso gravar em disco. Para dois números o DataStore atende; para histórico de partidas, um banco SQLite acessado pelo Room. Nos dois casos a leitura passa a ser assíncrona e a tela precisa tratar a espera pelo dado.

*4. Na sua opinião, qual abordagem você usaria em produção para este placar e por quê?*

R: A princípio eu usaria ViewModel + SavedStateHandle, que separa o estado da UI e sobrevive aos dois cenários testados. Para um app deste tamanho, porém, isso exige ViewModel, data class Parcelable e plugin adicional para manter dois contadores.

Por conta disso preferiria utilizar "by rememberSaveable": grava no mesmo Bundle, sobrevive à rotação e à morte do processo, e ocupa duas linhas. Voltaria ao ViewModel se o placar precisasse ser compartilhado entre telas ou passasse a ter lógica além de somar pontos.