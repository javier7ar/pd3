import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.io.File;


public class ServidorArchivosRMI implements IServidorArchivosRMI, Serializable {
	
	private static final long serialVersionUID = 1L;

	protected ServidorArchivosRMI() throws RemoteException {
		super();
	}
	
	@Override
	public int Leer(String nombreArchivo, int posicion, int cantidad, byte[] buffer) throws RemoteException {
		
		File file = new File(nombreArchivo);
		int cantLeida = 0;
		try
		{
			FileInputStream fin = new FileInputStream(file);
			
			byte fileContent[] = new byte[cantidad];
			 
			cantLeida = fin.read(fileContent,posicion,cantidad);
			System.arraycopy(fileContent,0,buffer,0,cantLeida);
			 
			// Para ver el contenido del archivo
			/*
			String strFileContent = new String(buffer);
			 
			System.out.println("Bytes Leidos : "+cantLeida);
			System.out.println("File content : ");
			System.out.println(strFileContent);
			*/
			fin.close();
		 
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
		FileOutputStream salida;		
		int cant;
		
		try{						
			//si el archivo existe lo abre, sino lo crea
			salida = new FileOutputStream(nombreArchivo, (new File(nombreArchivo)).exists());
			salida.write(buffer, 0, cantidad);
			//si pasa sin tirar error, pudo escribir todo
			cant = cantidad;
			salida.close();
		}catch(Exception e){
			cant = -1;
		}
		return cant;
	}

}
