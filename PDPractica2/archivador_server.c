/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "archivador.h"
#include <stdio.h>

int *
escribir_1_svc(Args_Escribir *argp, struct svc_req *rqstp)
{
	static int  result;
	FILE *archivo;
	int i, j;
	char contenido[1000];
	int caracter;
	
	archivo = fopen(argp->nombreArchivo, "a" );
	if (archivo==NULL) {
		result = -1; /*indico el error con longitud negativa*/
	}
	else{   /*pudo abrir el archivo, recupero el contenido hasta posicion o hasta el máximo*/
		/*i = 0;
		while ((caracter = fgetc(archivo) != EOF) && (i < argp->posicion)){
			contenido[i]= (char)caracter;
			i = i + 1;
	    	}
		fclose(archivo);*/
		i = read(archivo, contenido, 1000);
		/*n = read(sockfd,buffer,255);*/
		archivo = fopen (argp->nombreArchivo, "w" ); 
		j = 0;
		while(i < argp->dato.long_datos){
			contenido[i] = argp->dato.buffer[j];
			j = j + 1;
			i = i + 1;	
		}		
			fputs(contenido, archivo);				
		fclose(archivo);
	}

	return &result;
}

Datos *
leer_1_svc(Args_Leer *argp, struct svc_req *rqstp)
{
	static Datos result;
	int i;
	char caracter;
	char contenido[1000];
	int max, j, cont;

	FILE *archivo;/*El manejador de archivo*/

	archivo=fopen(argp->nombreArchivo, "r");
	if(archivo==NULL){ /*no pudimos abrir el archivo*/
        	result.long_datos = -1;	
	}
	else{/*recupero el archivo*/
		i = 0;
		while (feof(archivo) == 0){
			caracter = fgetc(archivo);
			
			contenido[i]= caracter;
			i = i + 1;
	    	}
		if((argp->posicion + argp->long_datos) > i){
			max = i;	
		}
		else{
			max = argp->posicion + argp->long_datos;
		}
		cont = 0;
		for(j = argp->posicion; j < max; j++){
			result.buffer[cont] = contenido[j];
			cont = cont + 1;		
		}
		result.long_datos = cont;	
	}
	fclose(archivo);/*Cerramos el archivo*/
	return &result;
}



