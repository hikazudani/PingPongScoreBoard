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
| ViewModel + StateFlow        |                 |                   |
| ViewModel + SavedStateHandle |                 |                   |

## Perguntas

1. Por que o ViewModel sozinho (etapas 2 e 3) não é suficiente para sobreviver à morte do processo, mesmo sobrevivendo à rotação de tela?

   **R:**

2. Qual a diferença prática entre usar mutableStateOf e StateFlow dentro do ViewModel nesta aplicação? Em algum momento essa diferença foi perceptível nos testes?

   **R:**

3. Se este placar precisasse ser salvo permanentemente (mesmo após o usuário fechar o app e abrir dias depois), qual das quatro abordagens ainda seria insuficiente, e o que seria necessário adicionar?

   **R:**

4. Na sua opinião, qual abordagem você usaria em produção para este placar e por quê?

   **R:**