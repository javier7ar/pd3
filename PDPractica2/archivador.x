/* Definicion, en XDR, del servicio “Archivador”
contenida en el archivo
archivador.x
*/
typedef string filename<255>;
struct Datos {	 		
		int long_datos;
		char buffer[1000];		
	     };

struct Args_Escribir {		
                filename nombreArchivo;
		int posicion;
		Datos dato;
		     };

struct Args_Leer { 
		filename nombreArchivo;
		int posicion;
		int long_datos;
		    };

program ARCHIVADOR{
	version VERSION_ACTUAL{
		int ESCRIBIR (Args_Escribir)=1;
		Datos LEER (Args_Leer)=2;
		}=1;
	}=1;
