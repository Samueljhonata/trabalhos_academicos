#include <stdio.h>
#include <stdlib.h>
#define N 9
#define BRANCO 0

int resolucao(int **quadro,int tam, int *tentativa,int *recursao);

void exibe_Quadro(int **quadro,int tam);

int verifica_Linha(int **quadro,int tam, int linha,int num);

int verifica_Coluna(int **quadro,int tam, int coluna,int num);

int verifica_Caixa(int **quadro, int Linha_Inicial, int Coluna_Inicial,int tam);

int salva_possibilidade(int **quadro,int tam, int linha, int coluna,int num);

int localizacao(int **quadro,int tam, int *linha, int *coluna);

