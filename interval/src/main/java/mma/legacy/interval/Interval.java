package mma.legacy.interval;

import java.util.logging.Logger;

/**
 * Clase para el ejemplo de trabajo con Legacy
 * @author Agustin
 * Controla operaciones sobre intervalos que pudeen ser abiertos o cerrados
 */
public class Interval {

	private double minimum;  // número entero que indica el limite superior del intervalo
	private double maximum;  // número entero que indica el limite superior del intervalo
	private Opening opening; // Valor booleano que indica si el intervalo es abierto o cerrado

	/**
	 * Constructor de la clase
	 * @param minimum
	 * @param maximum
	 * @param opening
	 * Todos los parámetros pueden ser nulos
	 */
	public Interval(double minimum, double maximum, Opening opening) 
	{
		this.minimum = minimum;
		this.maximum = maximum;
		this.opening = opening;
		System.out.println("Objeto creado");
	}

	/**
	 * Este método calcula el punto medio entre el valor maximo y minimo
	 * @return el punto medio entre el intervalo maximo y minimo
	 */
	public double midPoint() 
	{		
		return (maximum + minimum) / 2;	
	}

	/**
	 * Este método mira si un valor está dentro de un determiando intervalo
	 * @param valor para consultar en que intervalo esta
	 * @return si esta en dentro del intervalo
	 */
	public boolean includes(double value) 
	{
		System.out.println("Entro en el método");	
		
		switch (opening) 
		{
			case BOTH_OPENED:			return minimum < value && value < maximum;
			
			case LEFT_OPENED: 			return minimum < value && value <= maximum;
			
			case RIGHT_OPENED:			return minimum <= value && value < maximum;
			
			case UNOPENED:				return minimum <= value && value <= maximum;
			
			default:					assert false;
			
			return false;
		}
	}

/**
	 * Este método calcula si un intervalo está dentro de un intervalo
	 * @param intervalo 
	 * @return si esta dentro del intervalo
 */
	public boolean includes(Interval interval) 
	{
		
		boolean minimumIncluded = this.includes(interval.minimum);
		boolean maximumIncluded = this.includes(interval.maximum);
		
		switch (opening) 
		{
			case BOTH_OPENED:
								switch (interval.opening) 
								{
									case BOTH_OPENED:	return (minimumIncluded || minimum == interval.minimum)
																&& (maximumIncluded || maximum == interval.maximum);
									case LEFT_OPENED:	return (minimumIncluded || minimum == interval.minimum)
																&& (maximumIncluded);
									case RIGHT_OPENED:	return (minimumIncluded)
																&& (maximumIncluded || maximum == interval.maximum);
									case UNOPENED:		return (minimumIncluded) && (maximumIncluded);
									
									default:			assert false;
									return false;
								}
			case LEFT_OPENED:
								switch (interval.opening) 
								{
									case BOTH_OPENED: 		return (minimumIncluded || minimum == interval.minimum)	
																	&& (maximumIncluded || maximum == interval.maximum);
									
									case LEFT_OPENED:		return (minimumIncluded || minimum == interval.minimum) 
																	&& (maximumIncluded || maximum == interval.maximum);
									
									case RIGHT_OPENED:		return (minimumIncluded) 
																	&& (maximumIncluded || maximum == interval.maximum);
									
									case UNOPENED:			return (minimumIncluded) 
																	&& (maximumIncluded || maximum == interval.maximum);
									
									default:
												assert false;
												return false;
								}
		
			case RIGHT_OPENED:							
								switch (interval.opening)
								{
									case BOTH_OPENED: 	return (minimumIncluded || minimum == interval.minimum)
															&& (maximumIncluded || maximum == interval.maximum);
									case LEFT_OPENED:	return (minimumIncluded || minimum == interval.minimum)
															&& (maximumIncluded);
									case RIGHT_OPENED:	return (minimumIncluded || minimum == interval.minimum)
														&& (maximumIncluded || maximum == interval.maximum);
									case UNOPENED:		return (minimumIncluded || minimum == interval.minimum)
														&& (maximumIncluded);
									default:
										assert false;
										return false;
								}
			case UNOPENED:
								switch (interval.opening) 
								{
									case BOTH_OPENED: 	return (minimumIncluded || minimum == interval.minimum) 
																&& (maximumIncluded || maximum == interval.maximum);
									
									case LEFT_OPENED: 	return (minimumIncluded || minimum == interval.minimum)
																&& (maximumIncluded || maximum == interval.maximum);
			
									case RIGHT_OPENED: 	return (minimumIncluded || minimum == interval.minimum)
																&& (maximumIncluded || maximum == interval.maximum);
			
									case UNOPENED:		return (minimumIncluded || minimum == interval.minimum)
																&& (maximumIncluded || maximum == interval.maximum);
									default:
											assert false;
											return false; 
								}
		default:
			assert false;
			return false;
		}
	}

	/**
	 * Este método calcula el ancho de un intervalo
	 * @param interval
	 * @return
	 */

	public boolean intersectsWith(Interval interval) 
	{
		if (minimum == interval.maximum) 
		{
			switch (opening) 
			{
				case BOTH_OPENED:
				case LEFT_OPENED:	return false;
			
				case RIGHT_OPENED:
				case UNOPENED:		return interval.opening == Opening.LEFT_OPENED ||
									interval.opening == Opening.UNOPENED;
				default:
					assert false;
					return false;	
			}
		}
		
		if (maximum == interval.minimum) 
		{
			switch (opening) 
			{
				case BOTH_OPENED:
				case RIGHT_OPENED:	return false;
			
				case LEFT_OPENED:
				case UNOPENED:		return interval.opening == Opening.RIGHT_OPENED || interval.opening == Opening.UNOPENED;
				
				default:
						assert false;
						return false;
			}
		}
		return this.includes(interval.minimum)|| this.includes(interval.maximum);
	}

																																													@Override
	public String toString() {
		// TODO
		return null;
	}

																																																																																	@Override
	public boolean equals(Object object) {
		// TODO
		return false;
	}

}
