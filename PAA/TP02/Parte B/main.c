/*Como todos os outros problemas de Backtracking, podemos resolver o Sudoku de um em um,
atribuindo números a células vazias. Antes de atribuir um número, verificamos se é seguro atribuir.
Basicamente, verificamos que o mesmo número não está presente na linha atual, coluna atual e sub-rede 3X3 atual.
Após verificar a segurança, atribuímos o número e verificamos recursivamente se essa atribuição leva a uma solução ou não.
Se a atribuição não levar a uma solução, tentaremos o próximo número para a célula vazia atual.
E se nenhum número (1 a 9) leva a uma solução, retornamos falso.*/

#include <stdio.h>
#include <stdlib.h>
#include "sudoku.h"

int main() {
    /*Quadro para achar a solução.*/
	int quadro[N][N] = {{0, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};

    printf("SUDOKU INICIO\n");
    exibe_Quadro(quadro);
    /*Recebe 1 se há solucao e 0 se não*/
	if (resolucao(quadro)) {
        printf("\nSUDOKU RESOLVIDO\n");
		exibe_Quadro(quadro);
	} else {
		printf("\nNão há solucao!\n");
        exibe_Quadro(quadro);
	}

	return 0;
}
