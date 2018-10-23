#ifndef CACA_PALAVRAS_H
#define CACA_PALAVRAS_H

#include "pilha.h"

#define INICIO 0
#define BAIXO 1
#define ESQUERDA 2
#define DIREITA 3

#define NORMAL 1
#define ANALISE 2


typedef struct Tabuleiro{
    char **tabuleiro;
    int qtdLinhas;
    int qtdColunas;
} Tabuleiro;

char** alocarMatriz(int Linhas, int Colunas);
int stringToInt(char *num);
int carregaArquivo(char *nomeArq, Tabuleiro *retorno);
void copiaTabuleiro(Tabuleiro *original, Tabuleiro *copia);
void tabuleiroZerado(Tabuleiro *tabuleiro, int qtdLinhas, int qtdColunas);
void mostraTabuleiro(Tabuleiro *tabuleiro);
Posicao posiciona(int local, Posicao posicao);
TipoItem montaItem(Posicao posicaoAtual, int tipoMovimento);
void cacaPalavraRecursivo(Tabuleiro *tabuleiro, char *palavra, int letraAtual, Posicao posicaoAtual, int movimentoAnterior, TipoPilha *pilha, int *qtdPalavras, int *contaChamadas);
void cacaPalavra(Tabuleiro *tabuleiro, char *palavra, int modo);

#endif /* CACA_PALAVRAS_H */

