/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "archivador.h"

bool_t
xdr_filename (XDR *xdrs, filename *objp)
{
	register int32_t *buf;

	 if (!xdr_string (xdrs, objp, 255))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_Datos (XDR *xdrs, Datos *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_int (xdrs, &objp->long_datos))
		 return FALSE;
	 if (!xdr_vector (xdrs, (char *)objp->buffer, 1000,
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_Args_Escribir (XDR *xdrs, Args_Escribir *objp)
{
	register int32_t *buf;

	 if (!xdr_filename (xdrs, &objp->nombreArchivo))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->posicion))
		 return FALSE;
	 if (!xdr_Datos (xdrs, &objp->dato))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_Args_Leer (XDR *xdrs, Args_Leer *objp)
{
	register int32_t *buf;

	 if (!xdr_filename (xdrs, &objp->nombreArchivo))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->posicion))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->long_datos))
		 return FALSE;
	return TRUE;
}
