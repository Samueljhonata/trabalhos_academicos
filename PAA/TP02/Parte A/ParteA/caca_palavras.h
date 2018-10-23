#ifndef CACA_PALAVRAS_H
#define CACA_PALAVRAS_H

//#include <stdio.h>
//#include <stdlib.h>
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


#endif /* CACA_PALAVRAS_H */

