#include "sudoku.h"

void exibe_Quadro(int quadro[N][N]) {
	int linha, coluna;
	for (linha = 0; linha < N; linha++) {
		for ( coluna = 0; coluna < N; coluna++) {
			printf("%2d", quadro[linha][coluna]);
		}
		printf("\n");
	}
}

/* Utiliza uma grade parcialmente preenchida e tenta atribuir valores a
   todos os locais não atribuídos de forma a satisfazer os requisitos
   para solução Sudoku (não duplicação entre linhas, colunas e caixas) */
int resolucao(int quadro[N][N]) {

	int linha = 0;
	int coluna = 0,num;

    // Procura por uma posição que não tenha tribuição
	if (!localizacao(quadro, &linha, &coluna)){
		return 1;
	}

    // considerar digitos 1 a N
	for ( num = 1; num <= N; num++ ) {
        // Se for o número
		if (salva_possibilidade(quadro, linha, coluna, num)) {
            // anota o número provisoriamente
			quadro[linha][coluna] = num;
            // Se a linha estiver bem colocada, retornamos
			if (resolucao(quadro)) {
				return 1;
			}
            //tenta novamente
			quadro[linha][coluna] = BRANCO;
		}
	}

	return 0;// Retrocede as operações
}

/*Verifica a possibilidade em toda a linha*/
/* Retorna um booleano que indica se uma entrada designada
    na linha especificada corresponde ao número fornecido */
int verifica_Linha(int quadro[N][N], int linha, int num){
	int coluna;
	for (coluna = 0; coluna < 9; coluna++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}
/* Retorna um booleano que indica se uma entrada designada
    na coluna especificada corresponde ao número fornecido.*/
int verifica_Coluna(int quadro[N][N], int coluna, int num) {
	int linha;
	for (linha = 0; linha < 9; linha++) {
		if (quadro[linha][coluna] == num) {
			return 1;
		}
	}
	return 0;
}
/*Verifica o quadrado 3x3 para verificar se o número está entre os 9*/
/* Retorna um booleano que indica se uma entrada designada
    dentro da caixa 3x3 especificada corresponde ao número indicado. */
int verifica_Caixa(int quadro[N][N], int Linha_Inicial, int Coluna_Inicial, int num) {
	int linha,coluna;
	for (linha= 0; linha < 3; linha++) {
		for (coluna = 0; coluna < 3; coluna++) {
			if (quadro[linha + Linha_Inicial][coluna + Coluna_Inicial] == num) {
				return 1;
			}
		}
	}
	return 0;
}


/* Retorna um booleano que indica se será legal atribuir
    num para a linha dada, col location. */
int salva_possibilidade(int quadro[N][N], int linha, int coluna, int num) {
	return !verifica_Linha(quadro, linha, num)
			&& !verifica_Coluna(quadro, coluna, num)
			&& !verifica_Caixa(quadro, linha - (linha % 3), coluna - (coluna %3), num);
}
/* Pesquisa a grade para encontrar uma entrada que ainda não foi atribuída. E se
    encontrado, a linha de parâmetros de referência, col será definido o local
    que não está atribuído e true é retornado. Se não houver entradas não atribuídas
    permanecer, falso é retornado.*/
int localizacao(int quadro[N][N], int *linha, int *coluna) {

	for (*linha = 0; *linha < N; (*linha)++) {
		for (*coluna = 0; *coluna < N; (*coluna)++) {
			if (quadro[*linha][*coluna] == BRANCO) {
				return 1;
			}
		}
	}
	return 0;
}
