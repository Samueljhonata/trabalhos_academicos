#include <string.h>
#include "caca_palavras.h"

//https://programacaodescomplicada.wordpress.com/2012/11/09/aula-64-alocacao-dinamica-pt-6-alocacao-de-matrizes/
//Adaptado
char** alocarMatriz(int Linhas, int Colunas) { //Recebe a quantidade de Linhas e Colunas como Parâmetro

    char i, j; //Variáveis Auxiliares

    char **m = (char**) malloc(Linhas * sizeof (char*)); //Aloca um Vetor de Ponteiros

    for (i = 0; i < Linhas; i++) { //Percorre as linhas do Vetor de Ponteiros
        m[i] = (char*) malloc(Colunas * sizeof (char)); //Aloca um Vetor de Inteiros para cada posição do Vetor de Ponteiros.
        for (j = 0; j < Colunas; j++) { //Percorre o Vetor de Inteiros atual.
            m[i][j] = 0; //Inicializa com 0.
        }
    }
    return m; //Retorna o Ponteiro para a Matriz Alocada
}

int stringToInt(char *num) {
    int i, n = 0;
    for (i = 0; i < strlen(num); i++) {
        n = (n * 10) + num[i] - 48;
    }
    return n;
}

int carregaArquivo(char *nomeArq, Tabuleiro *retorno) {
    FILE *arq;
    arq = fopen(nomeArq, "r");
    char ch;
    char numLinhas[5] = {}, numColunas[5] = {};
    int i = 0, cont = 0;
    int l = 0, c = 0;
    int atual = 0;

    if (arq == NULL) { //erro ao carregar arquivo
        printf("ERRO AO LER ARQUIVO");
        return 0;
    } else {
        while ((ch = fgetc(arq)) != EOF) { //enquanto o arquivo não termina
            //tamanhos de linha e coluna
            if (i == 0) { //olhando a qtd de linhas e colunas
                if (ch == '\n') {//termina de olhar qtd de linhas e colunas
                    i = 1;
                    numColunas[cont] = '\0';
                    retorno->qtdLinhas = stringToInt(numLinhas);
                    retorno->qtdColunas = stringToInt(numColunas);
                } else {
                    if (ch != ' ') {
                        if (atual == 0) {
                            numLinhas[cont] = ch;
                            cont++;
                        } else {
                            numColunas[cont] = ch;
                            cont++;
                        }
                    } else {
                        atual = 1;
                        numLinhas[cont] = '\0';
                        cont = 0;
                    }
                }
            } else { //depois de pegar qtd de linhas e colunas
                if (i == 1) { //aloca a matriz uma vez
                    retorno->tabuleiro = alocarMatriz(retorno->qtdLinhas, retorno->qtdColunas);
                    i = 2;
                }

                if (ch == '\n') {//quebra de linha
                    l++;
                    c = 0;
                } else { //pegando os caracteres

                    retorno->tabuleiro[l][c] = ch;
                    c++;
                }
            }
        }
    }

    fclose(arq);
    return 1;
}

void copiaTabuleiro(Tabuleiro *original, Tabuleiro *copia) {
    int i, j;
    copia->tabuleiro = alocarMatriz(original->qtdLinhas, original->qtdColunas);
    copia->qtdLinhas = original->qtdLinhas;
    copia->qtdColunas = original->qtdColunas;
    for (i = 0; i < original->qtdLinhas; i++) {
        for (j = 0; j < original->qtdColunas; j++) {
            copia->tabuleiro[i][j] = original->tabuleiro[i][j];
        }
    }
}

void tabuleiroZerado(Tabuleiro *tabuleiro, int qtdLinhas, int qtdColunas) {
    int i, j;
    tabuleiro->tabuleiro = alocarMatriz(qtdLinhas, qtdColunas);
    tabuleiro->qtdLinhas = qtdLinhas;
    tabuleiro->qtdColunas = qtdColunas;
    for (i = 0; i < qtdLinhas; i++) {
        for (j = 0; j < qtdColunas; j++) {
            tabuleiro->tabuleiro[i][j] = '*';
        }
    }
}

void mostraTabuleiro(Tabuleiro *tabuleiro) { //imprime o tabuleiro na tela
    int i, j;
    printf("\n Tamanho: n%dx%d\n", tabuleiro->qtdLinhas, tabuleiro->qtdColunas);
    printf(" ");
    for (i = 0; i <= tabuleiro->qtdColunas; i++) {
        printf("__");
    }

    printf("\n");
    for (i = 0; i < tabuleiro->qtdLinhas; i++) {


        for (j = 0; j < tabuleiro->qtdColunas; j++) {
            if (j == 0) {
                printf(" |");
            }
            printf("%c ", tabuleiro->tabuleiro[i][j]);
        }
        printf("|\n");
    }

    printf(" ");
    for (i = 0; i <= tabuleiro->qtdColunas; i++) {
        printf("__");
    }
}

Posicao posiciona(int local, Posicao posicao) {
    Posicao posicaoRetorno;
    switch (local) {
        case BAIXO:
            posicaoRetorno.linha = posicao.linha + 1;
            posicaoRetorno.coluna = posicao.coluna;
            break;
        case ESQUERDA:
            posicaoRetorno.linha = posicao.linha;
            posicaoRetorno.coluna = posicao.coluna - 1;
            break;
        case DIREITA:
            posicaoRetorno.linha = posicao.linha;
            posicaoRetorno.coluna = posicao.coluna + 1;
            break;
    }
    return posicaoRetorno;
}

int confereDentro(Posicao *p, int qtdLinhas, int qtdColunas){
    return (p->linha >= qtdLinhas || p->linha < 0 || p->coluna >= qtdColunas || p->coluna < 0);
}

TipoItem montaItem(Posicao posicaoAtual, int tipoMovimento) {
    TipoItem tItem;
    tItem.movimento = tipoMovimento;
    tItem.posicao.linha = posicaoAtual.linha;
    tItem.posicao.coluna = posicaoAtual.coluna;
    return tItem;
}

void cacaPalavraRecursivo(Tabuleiro *tabuleiro, char *palavra, int letraAtual, Posicao posicaoAtual, int movimentoAnterior, TipoPilha *pilha, int *qtdPalavras, int *contaChamadas) { //resolve o caça palavras a partir de uma posição inicial
    Posicao p;
    TipoItem tItem;
    (*contaChamadas)++;

    if (confereDentro(&posicaoAtual, tabuleiro->qtdLinhas, tabuleiro->qtdLinhas) || letraAtual >= strlen(palavra)){
        return;
    }

    //confere se caractere atual é igual ao da palavra
    if (tabuleiro->tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] == palavra[letraAtual]) {
        (letraAtual)++;
        tItem = montaItem(posicaoAtual, BAIXO);
        if (letraAtual == strlen(palavra)) {
            tItem.fim = 1;
        }else{
            tItem.fim = 0;
        }

        Empilha(tItem, pilha);
        
    }else{
        /*int i;
        for (i = 0; i < letraAtual; i++) { //desempilha o erro
            Desempilha(pilha, &tItem);
        }*/
        return;
    }

    if (letraAtual == strlen(palavra)) {
        (*qtdPalavras)++;
    }

    if (movimentoAnterior == INICIO) {
        int aux = *qtdPalavras;
        //movimenta pra esquerda
        p = posiciona(ESQUERDA, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, ESQUERDA, pilha, qtdPalavras, contaChamadas);

        //movimenta pra baixo
        p = posiciona(BAIXO, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, BAIXO, pilha, qtdPalavras, contaChamadas);
        
        //movimenta pra direita
        p = posiciona(DIREITA, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, DIREITA, pilha, qtdPalavras, contaChamadas);
        
        if (*qtdPalavras == aux) { //remove letra inicial sozinha
            DesempilhaSobras(pilha);
        }

        
    } else if (movimentoAnterior == BAIXO) {
        //movimenta pra esquerda
        p = posiciona(ESQUERDA, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, ESQUERDA, pilha, qtdPalavras, contaChamadas);

        //movimenta pra direita
        p = posiciona(DIREITA, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, DIREITA, pilha, qtdPalavras, contaChamadas);
    } else if (movimentoAnterior == ESQUERDA) {
        //movimenta pra baixo
        p = posiciona(BAIXO, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, BAIXO, pilha, qtdPalavras, contaChamadas);

    } else if (movimentoAnterior == DIREITA) {
        //movimenta pra baixo
        p = posiciona(BAIXO, posicaoAtual);
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, p, BAIXO, pilha, qtdPalavras, contaChamadas);
    }
}

void cacaPalavra(Tabuleiro *tabuleiro, char *palavra, int modo) { //faz as configurações iniciais e as chamadas para cada posição inicial
    int letraAtual = 0;
    int qtdPalavras = 0;
    int contaChamadas = 0;
    
    TipoPilha pilha;
    TipoItem tItem;
    Tabuleiro resolvido;
    Posicao posicaoAtual;
    posicaoAtual.linha = 0;
    posicaoAtual.coluna = 0;
    copiaTabuleiro(tabuleiro, &resolvido);
    FPVazia(&pilha);
    //cacaPalavraRecursivo(tabuleiro,palavra,&letraAtual,po)
    int i;
    for (i = 0; i < tabuleiro->qtdLinhas*tabuleiro->qtdColunas; i++) {
        cacaPalavraRecursivo(tabuleiro, palavra, letraAtual, posicaoAtual, INICIO, &pilha, &qtdPalavras, &contaChamadas);
        //(posicaoAtual.linha)++;
        (posicaoAtual.coluna)++;
        if (posicaoAtual.coluna >= tabuleiro->qtdColunas) {
            (posicaoAtual.linha)++;
            posicaoAtual.coluna = 0;
        }

    }
    
    tabuleiroZerado(&resolvido, tabuleiro->qtdLinhas, tabuleiro->qtdColunas);
    while (Tamanho(pilha) > 0) {
        Desempilha(&pilha, &tItem);
        resolvido.tabuleiro[tItem.posicao.linha][tItem.posicao.coluna] = tabuleiro->tabuleiro[tItem.posicao.linha][tItem.posicao.coluna];
    }
    printf("\n\nForam encontradas %d ocorrências para a palavra %s", qtdPalavras, palavra);
    if (modo == ANALISE) {
        printf(", em %d chamadas recursivas", contaChamadas);
    }

    mostraTabuleiro(&resolvido);
}
