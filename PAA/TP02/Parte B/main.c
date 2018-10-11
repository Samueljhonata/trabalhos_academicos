/*Como todos os outros problemas de Backtracking, podemos resolver o Sudoku de um em um,
atribuindo n�meros a c�lulas vazias. Antes de atribuir um n�mero, verificamos se � seguro atribuir.
Basicamente, verificamos que o mesmo n�mero n�o est� presente na linha atual, coluna atual e sub-rede 3X3 atual.
Ap�s verificar a seguran�a, atribu�mos o n�mero e verificamos recursivamente se essa atribui��o leva a uma solu��o ou n�o.
Se a atribui��o n�o levar a uma solu��o, tentaremos o pr�ximo n�mero para a c�lula vazia atual.
E se nenhum n�mero (1 a 9) leva a uma solu��o, retornamos falso.*/

#include <stdio.h>
#include <stdlib.h>
#include "sudoku.h"

int main() {
    /*Quadro para achar a solu��o.*/
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
    /*Recebe 1 se h� solucao e 0 se n�o*/
	if (resolucao(quadro)) {
        printf("\nSUDOKU RESOLVIDO\n");
		exibe_Quadro(quadro);
	} else {
		printf("\nN�o h� solucao!\n");
        exibe_Quadro(quadro);
	}

	return 0;
}
