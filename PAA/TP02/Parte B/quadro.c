#include "sudoku.h"
int quadro_Resolver(){
    int temp;
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

    printf("\n-------------QUADRO INICIAL-----------\n");

    exibe_Quadro(quadro);

    resultado(resolucao(quadro),quadro);

}

/*Função que decide o resultado*/
int resultado(int resulta,int quadro[N][N]){
    /*Recebe 1 se há solucao e 0 se não*/
	if (resulta) {
        printf("\n---------------SUDOKU RESOLVIDO-----------\n");
		exibe_Quadro(quadro);
	} else {
		printf("\n------------Não há solucao!----------------\n");
        exibe_Quadro(quadro);
	}
}
