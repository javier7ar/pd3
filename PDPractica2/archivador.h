/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _ARCHIVADOR_H_RPCGEN
#define _ARCHIVADOR_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


typedef char *filename;

struct Datos {
	int long_datos;
	char buffer[1000];
};
typedef struct Datos Datos;

struct Args_Escribir {
	filename nombreArchivo;
	int posicion;
	Datos dato;
};
typedef struct Args_Escribir Args_Escribir;

struct Args_Leer {
	filename nombreArchivo;
	int posicion;
	int long_datos;
};
typedef struct Args_Leer Args_Leer;

#define ARCHIVADOR 1
#define VERSION_ACTUAL 1

#if defined(__STDC__) || defined(__cplusplus)
#define ESCRIBIR 1
extern  int * escribir_1(Args_Escribir *, CLIENT *);
extern  int * escribir_1_svc(Args_Escribir *, struct svc_req *);
#define LEER 2
extern  Datos * leer_1(Args_Leer *, CLIENT *);
extern  Datos * leer_1_svc(Args_Leer *, struct svc_req *);
extern int archivador_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define ESCRIBIR 1
extern  int * escribir_1();
extern  int * escribir_1_svc();
#define LEER 2
extern  Datos * leer_1();
extern  Datos * leer_1_svc();
extern int archivador_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_filename (XDR *, filename*);
extern  bool_t xdr_Datos (XDR *, Datos*);
extern  bool_t xdr_Args_Escribir (XDR *, Args_Escribir*);
extern  bool_t xdr_Args_Leer (XDR *, Args_Leer*);

#else /* K&R C */
extern bool_t xdr_filename ();
extern bool_t xdr_Datos ();
extern bool_t xdr_Args_Escribir ();
extern bool_t xdr_Args_Leer ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_ARCHIVADOR_H_RPCGEN */
