#ifndef PILHA_H
#define PILHA_H
//http://www2.dcc.ufmg.br/livros/algoritmos/cap3/codigo/c/3.13a3.14-pilha-apontadores.c
#include <stdio.h>
#include <stdlib.h>

//#include "caca_palavras.h"

typedef struct Posicao{
    int linha;
    int coluna;
} Posicao;

typedef int TipoChave;

typedef struct {
    int movimento;
    Posicao posicao;
    int fim;
} TipoItem;
typedef struct TipoCelula *TipoApontador;

typedef struct TipoCelula {
    TipoItem Item;
    TipoApontador Prox;
} TipoCelula;

typedef struct {
    TipoApontador Fundo, Topo;
    int Tamanho;
} TipoPilha;

void FPVazia(TipoPilha *Pilha);
int Vazia(TipoPilha Pilha);
void Empilha(TipoItem x, TipoPilha *Pilha);
void Desempilha(TipoPilha *Pilha, TipoItem *Item);
int Tamanho(TipoPilha Pilha);
void Imprime(TipoPilha Pilha);
void DesempilhaSobras(TipoPilha *Pilha);

#endif /* PILHA_H */

