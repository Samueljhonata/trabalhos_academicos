#include <stdio.h>
#include <stdlib.h>
#define N 9
#define BRANCO 0

int resolucao(int quadro[N][N]);

void exibe_Quadro(int quadro[N][N]);

int verifica_Linha(int quadro[N][N], int row, int num);

int verifica_Coluna(int quadro[N][N], int col, int num);

int verifica_Caixa(int quadro[N][N], int Linha_Inicial, int Coluna_Inicial, int num);

int salva_possibilidade(int quadro[N][N], int linha, int coluna, int num);

int localizacao(int quadro[N][N], int *linha, int *coluna);
