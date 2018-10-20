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
    int escolha;

    /*Menu de opções juntamente com a entrada do usuário com a opção de jogo.*/
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
void menu(){//Menu de opções
    printf("*********************************************\n");
    printf("\t\t->Bem Vindo aos jogos!<-\n");
    printf("\t\t\t1-Caça Palavras \n \t\t\t2-SUDOKU\n \t\t\t0-Sair");
    printf("\n*********************************************\n");
}
