import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.io.File;


public class ServidorArchivosRMI implements IServidorArchivosRMI, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static String rutaArchivosServidor = "C:\\";
	
	protected ServidorArchivosRMI() throws RemoteException {
		super();
	}
	
	@Override
	public int Leer(String nombreArchivo, int posicion, int cantidad, byte[] buffer) throws RemoteException {
		
		String archivo = ServidorArchivosRMI.rutaArchivosServidor + nombreArchivo;
		File file = new File(archivo);
		int cantLeida = 0;
		
		try
		{
			//si el archivo existe
			if((new File(archivo)).exists()){
				RandomAccessFile arch = new RandomAccessFile(archivo, "r");
				if(arch.length() > (posicion)){
					arch.seek(posicion);
					cantLeida = arch.read(buffer, 0, cantidad);
					arch.close();
				}
				else{
					System.out.println("posicion incorrecta");
					cantLeida = 0;
				}
			}
			else{
				System.out.println("archivo no existe: "+archivo);
				cantLeida = 0;
				}
			/*			
			FileInputStream fin = new FileInputStream(file);
			
			byte fileContent[] = new byte[cantidad];
			 
			cantLeida = fin.read(fileContent,posicion,cantidad);
			System.arraycopy(fileContent,0,buffer,0,cantLeida);
			 */
			// Para ver el contenido del archivo
			/*
			String strFileContent = new String(buffer);
			 
			System.out.println("Bytes Leidos : "+cantLeida);
			System.out.println("File content : ");
			System.out.println(strFileContent);
			*/			
		 
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Archivo no encotrado " + e);
		}
		catch(IOException ioe)
		{
			System.out.println("Error al leer el archivo " + ioe);
		}
		
		return cantLeida;
	}

	@Override
	public int Escribir(String nombreArchivo, int cantidad, byte[] buffer) throws RemoteException {		
		String archivo = ServidorArchivosRMI.rutaArchivosServidor + nombreArchivo;
		FileOutputStream salida;		
		int cant;
		
		try{	
			System.out.println("buffer: "+ new String(buffer));
			//si el archivo existe lo abre, sino lo crea
			salida = new FileOutputStream(archivo, (new File(archivo)).exists());
			salida.write(buffer, 0, cantidad);			
			//si pasa sin tirar error, pudo escribir todo porque write no devuelve cantidad escrita
			cant = cantidad;			
			salida.flush();
			salida.close();
		}catch(Exception e){
			cant = 0;
			System.out.println("Excepcion " + e);
		}
		System.out.println("cantidad escrita: "+String.valueOf(cant));
		return cant;
	}

}
