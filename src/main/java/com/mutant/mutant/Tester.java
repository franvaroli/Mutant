package com.mutant.mutant;

import org.springframework.stereotype.Component;

@Component
public class Tester {

	public boolean isMutant(String[] dna) throws Exception {
		if (dna.length != 6) {
			throw new Exception("El array 'dna' no cumple con el tamaño deseado");
		}
		for (int i=0; i < 6; i++){
			if (dna[i].length() != 6) {
				throw new Exception("El " + (i + 1) + "º string del array 'dna' no cumple con el tamaño deseado");
			}
		}
		
		boolean mutant = false;
		int total = 0;
		int occurr = 0;
		char param;
		//Primero busco de forma vertical y oblicua
		//Direcciones en las que buscar
		int[] x = {0,0,1,-1,1,-1};
		int[] y = {1,-1,1,-1,-1,1};
		
		//Recorro todas las letras del 3er String
		for (int i = 0; i < 6; i++) {
			//Tomo como parametro la letra en la que estoy ubicado
			param = dna[2].charAt(i);
			//Recorro las seis direcciones
			for (int dir = 0; dir < 6; dir++) {
				//Reseteo el contador entre vertical y oblicuo
				if (dir == 0 || dir == 2) {
					occurr = 0;
				}
				//Movimientos entre filas y columnas
				int row = i + x[dir], col = 2 + y[dir];
				//Maximo movimiento necesario en esa direccion
				for (int k = 0; k < 3; k++) {
					//Para no salirse de los limites
					if (row > 5 || row < 0 || col > 5 || col < 0) {
                        break;
					}
					//Si deja de coincidir cambia de direccion
					if (dna[col].charAt(row) != param) {
						break;
					}
					row += x[dir];
                    col += y[dir];
					occurr++;
				}
				//Si encuentra 4 o mas ocurrencias suma al total
				if (occurr >= 3) {
					total++;
				}
			}
		}
		
		//Luego busco de forma horizontal
		//Direcciones en las que buscar
		int[] h = {1,-1};
		//Recorro todos los Strings
		for (int i = 0; i < 6; i++) {
			//Tomo como parametro la 3er letra
			param = dna[i].charAt(2);
			occurr = 0;
			//Recorro las dos direcciones
			for (int dir = 0; dir < 2; dir++) {
				//Si para la derecha no encontro ocurrencias sale
				if (dir == 1 && occurr == 0) {
					break;
				}
				//Movimiento en la fila
				int row = 2 + h[dir];
				//Maximo movimiento necesario en esa direccion
				for (int k = 0; k < 3; k++) {
					if (row > 5 || row < 0) {
                        break;
					}
					//Si deja de coincidir cambia de direccion
					if (dna[i].charAt(row) != param) {
						break;
					}
					row += h[dir];
					occurr++;
				}
			}
			//Si encuentra 4 o mas ocurrencias suma al total
			if (occurr >= 3) {
				total++;
			}
		}
		
		
		if (total >= 2) {
			mutant = true;
		}
		return mutant;
	}
}