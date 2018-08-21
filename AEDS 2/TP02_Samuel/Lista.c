/*******************************************************
    UNIVERSIDADE FEDERAL DE VI�OSA - CAMPUS FLORESTAL
    CI�NCIA DA COMPUTA��O - 2016/1

    TRABALHO PR�TICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Gl�cia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include "Lista.h"
#define UNIVEL 3
#define ULTIMONIVEL 125

/*cria lista vazia*/
TLista* FLVazia(TLista* listanoH, int nivel)
{
    listanoH = (TLista*) malloc(sizeof(TLista));
    listanoH->pPrimeiro = (Apontador) malloc(sizeof(TCelula));
    listanoH->pUltimo = listanoH->pPrimeiro;
    listanoH->nivel = nivel;
    listanoH->pPrimeiro->pProx = NULL;
    return listanoH;
}

/*insere na lista*/
void LInsere(TLista *pLista,TItem pItem)
{
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pUltimo->pProx;
    pLista->pUltimo->Item = pItem;
    pLista->pUltimo->pProx = NULL;
    pLista->Contador++;
}

/*atualiza e imprime os n�veis da �rvore*/
void LImprime(TLista* pLista, GtkWidget *label[][ULTIMONIVEL])
{
    int i, j, anterior, cont=0, numlabel=0;
    Apontador pAux;

    /*ve em qual n�vel est�*/
    if(pLista->nivel == 0)
    {
        anterior =-99;
    }
    else
    {
        anterior = -1;
    }

    /*caso nao existam paginas no nivel*/
    if (pLista->pPrimeiro == pLista->pUltimo)
    {
        return;
    }

    /*pescorre todas as p�ginas do n�vel*/
    pAux = pLista->pPrimeiro->pProx;
    while (pAux != NULL)
    {
        if(anterior == -99)
        {
            /*Caso esteja no primeiro n�vel*/
            gtk_label_set_text(GTK_LABEL(label[0][0]), pAux->Item.pagina);
            anterior = 0;
        }
        else
        {
            /*olha se pai tem o m�ximo de filhos poss�vel*/
            if(anterior > pAux->Item.filhoNum)
            {
                while(anterior<4)
                {
                    /*caso n�o tenha o m�ximo, pula as labels correspondentes*/
                    anterior++;
                }
                cont++;
            }
            numlabel = pAux->Item.filhoNum + (cont*5);

            /*atualiza a label correspondente � p�gina*/
            gtk_label_set_text(GTK_LABEL(label[pLista->nivel][numlabel]), pAux->Item.pagina);
            anterior = pAux->Item.filhoNum;
        }
        pAux = pAux->pProx; /* pr�xima c�lula */
    }
}
