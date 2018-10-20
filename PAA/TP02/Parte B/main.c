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
    int escolha;

    /*Menu de op��es juntamente com a entrada do usu�rio com a op��o de jogo.*/
    do{
        menu();
        scanf("%d",&escolha);

        switch(escolha){
            case 1:
                break;
            case 2: quadro_Resolver();
                break;
        }

    }while(escolha!=0);

	return 0;
}
void menu(){//Menu de op��es
    printf("*********************************************\n");
    printf("\t\t->Bem Vindo aos jogos!<-\n");
    printf("\t\t\t1-Ca�a Palavras \n \t\t\t2-SUDOKU\n \t\t\t0-Sair");
    printf("\n*********************************************\n");
}
