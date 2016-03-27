package controle.Visual;

import java.util.ArrayList;

import conexao.Conexao;
import Modelos.Departamento;
import Modelos.Subdepartamento;

public class DepartamentosControle {
	
	private static final DepartamentosControle departamentosControle = new DepartamentosControle();
	
	public static DepartamentosControle get(){
		return departamentosControle;
	}
	
	public ArrayList<Departamento> buscarDepartamento(){
		
		ArrayList<Departamento> arrayList = new ArrayList<Departamento>();
		arrayList = Conexao.getReference().buscarDepartamento();
		return arrayList;
	}

	public ArrayList<ArrayList<Subdepartamento>> buscarSubdepartamentos(
			ArrayList<Departamento> departamentos) {

		ArrayList<ArrayList<Subdepartamento>> arrayList = new ArrayList<ArrayList<Subdepartamento>>();
		
		for (Departamento departamento : departamentos) {
			
			arrayList.add((ArrayList<Subdepartamento>) Conexao.getReference().buscarSubdepartamento(departamento));
		}
		
		return arrayList;
	}

}
