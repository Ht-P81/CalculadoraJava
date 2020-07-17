package M07UF1Pac12;

//Todos los paquetes que hemos necesitados importar
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


// Clase calculadora que hereda de Jframe que genera la ventana sobre la que hacer la calculadora
public class Calculadora extends JFrame {
	
	// Se crea esta variable para la serialización de la clase
	// Se comprueba que las versiones sean las mismas 
	private static final long serialVersionUID = 1L;

	
	// ---------DECLARACION DE COMPONENTES------ //
	// Mediante un JTextField o "campo de texto" agregamos la Pantalla que mostrará
	// la información de la calculadora
	JTextField pantalla;
	// Mediante la declaración de los JPanel iremos agregando teclas y organizando
	// contenido a la calculadora
	JPanel PanelOperaciones;
	JPanel PanelNumeros;

	
	// -------DECLARACIÓN DE VARIABLES------ //
	// Resto de variables que nos ayudarán a aplicar la funcionalidad a la
	// calculadora
	double resultado; //se aplica tipo double por si se obtienen decimales, se utilizará para operaciones (después del =)
	boolean nuevaOperacion = true; // variable sobre la que continuar operando
	String operacion; // variable sobre la que se desarrolla el método con las diferentes opciones de cálculo
	double cuadrado;

	
	// ------- CONSTRUCTOR CALCULADORA ---------//
	public Calculadora() {
		super(); //la llamada al constructor del padre en este caso Jframe, para extender las funcionalidades de esta clase
	
		
		// ----VENTANA-----//
		// Especificamos información del JFrame (la ventana)
		setSize(500, 600); // Tamaño de nuestra ventana
		setResizable(true); // Reajustable en tamaño
		setTitle("Calculadora M07UF1PAC12 Jose Antonio Nieto Morales"); // Título de la ventana
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Método de JFrame que indica que se acaba el programa

		
		// ----PANEL PRINCIPAL------//
		// Especificamos información del JPanel
		// con getContentPane añaden elementos del JPanel al JFrame (ventana)
		// contenedor de componentes para añadir funcionalidades
		JPanel panel = (JPanel) this.getContentPane(); // Se construye el panel
		panel.setLayout(new BorderLayout()); // con esto divide la ventana en 5 partes (layout)
		panel.setBackground(Color.lightGray); // colocamos color de fondo

		
		// ----PANEL SUPERIOR PANTALLA---//
		// Vamos agregando y definiendo información sobre la pantalla
		// El constructor de JTextField recibe 2 parámetros, uno el texto por defecto y
		// otro el número de caracteres que puede abarcar
		pantalla = new JTextField(" ", 20); // Anchura suficiente para 20 caracteres
		// Set border, no es método propio de JTextField pero sí de JComponent, permite
		// colocar diferentes bordes a un componente visible
		pantalla.setBorder(new EmptyBorder(10, 10, 10, 10));
		// Se estable el diseño (fuente, estilo, tamaño)
		pantalla.setFont(new Font("Calibri", Font.BOLD, 25));
		// Con este método podemos asignarle un color al texto del elemento pantalla
		pantalla.setForeground(Color.white); // números pantalla blancos
		// Con este vamos asignándole a los números de la pantalla alineación a la derecha
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		// No se permite que el usuario introduzca texto.
		pantalla.setEditable(false); // Se evita  que se puedan escribir letras
		pantalla.setBackground(new java.awt.Color(186, 85, 211)); // Color de fondo pantalla
		Border miBorde = new LineBorder(new java.awt.Color(221, 160, 221), 20); // líneas de separación entre teclas numéricas
		pantalla.setBorder(miBorde); // se establecen los bordes
		pantalla.setPreferredSize(new Dimension(250, 100)); // con esto ajustamos los tamaños definidos por el BorderLayout
		panel.add("North", pantalla); // Se establece situación superior(Norte)
	
		
		// ----PANEL CENTRAL NUMEROS-----//
		// Se establece otro panel para los botones numéricos
		PanelNumeros = new JPanel();
		// se anida un gridlayout(tabla) dentro del JPanel y se dice que tiene 4 filas y 3 columnas
		PanelNumeros.setLayout(new GridLayout(4, 3));
		PanelNumeros.setBorder(new EmptyBorder(1, 16, 16, 16)); // se establecen el tamaño de los bordes
		PanelNumeros.setBackground(new java.awt.Color(221, 160, 221)); // color de fondo, se especifica código de color RGB

		// Se contruyen todos los botones numéricos
		// Se le pasa un String como parámetro como así se ha especificado en su método más abajo
		// Se le asigna el valor que representará en la calculadora 
		Tecla("7");
		Tecla("8");
		Tecla("9");
		Tecla("4");
		Tecla("5");
		Tecla("6");
		Tecla("1");
		Tecla("2");
		Tecla("3");
		Tecla("0");
		Tecla(".");
		Tecla("C");

		// Se establece situación centrada
		panel.add("Center", PanelNumeros);

		
		// ----PANEL LATERAL EAST OPERACIONES-----//
		// Panel para las teclas laterales referentes a las operaciones llamadas
		// acciones
		PanelOperaciones = new JPanel(); // Panel para los botones de operaciones
		PanelOperaciones.setLayout(new GridLayout(6, 1)); // Se establece el gridlayout con 2 filas y 3 columnas
		PanelOperaciones.setBorder(new EmptyBorder(1, 1, 16, 16)); // Se estable el borde al componente
		PanelOperaciones.setBackground(new java.awt.Color(221, 160, 221)); // Se especifica el color de fondo
		PanelOperaciones.setPreferredSize(new Dimension(100, 100)); // Reajustamos el tamaño de este panel para darle proporción

		// Se hace todos los botones del panel lateral operación, nombre del método creado más abajo
		// se le pasan los dos parámetros, el primero, "NombreTecla" es el texto que lleva la
		// tecla en sí, el segundo la "InfoTecla", que es lo que hace la tecla cuando se deja el ratón encima de ella
		TeclaOperacion("+", "Sumar");
		TeclaOperacion("-", "Restar");
		TeclaOperacion("*", "Multiplicar");
		TeclaOperacion("/", "Dividir");
		TeclaOperacion("^2", "Calcular cuadrado");
		TeclaOperacion("=", "Obtener resultado");
	
		// Se agregan la ubicación
		panel.add("East", PanelOperaciones);
	
		// Se validan todos los paneles
		validate();

	} // cerramos el constructor cálculadora

	
	// -------METODOS PARA DEFINIR DISEÑOS A LAS TECLAS PANEL CENTRAL------ //
	// Método para diseñar los botones de números en el panel central
	private void Tecla(String numero) {
		JButton boton = new JButton(); // se instancia un JButton
		boton.setText(numero); // se le coloca el numero que se le asigne
		// Se le agrega fuente y tamaño
		Font nuevaFuenteTamanoMayor=new Font(boton.getFont().getName(),Font.ITALIC, 20);  
		boton.setFont(nuevaFuenteTamanoMayor);
		boton.setForeground(Color.white);//color de los números en blanco
		boton.setBackground(new java.awt.Color(193, 102, 255));// color lila claro
		// líneas de separación entre teclas numéricas
		boton.setBorder(new LineBorder(new java.awt.Color(221, 160, 221), 10)); // se establecen los bordes
		
		//Se le añade el listener del raton
		boton.addMouseListener(new MouseAdapter() {//listener el que ve si alguien levanta la bandera
			//mouseadapter el que dice lo que se hace cuando se levante la bandera
			
			// Método para agregar la funcionalidad de obtener por pantalla el número pulsado en la tecla
			@Override
			public void mouseReleased(MouseEvent evento) {//el evento cuando se levanta la bandera
				JButton botonevento = (JButton) evento.getSource();
				numeroPulsado(botonevento.getText());
			}
		}); //esto es de la clase anónima
		PanelNumeros.add(boton); //se agrega la información al panel de los números (central)
	}

	
	// -------METODOS PARA DEFINIR DISEÑOS A LAS TECLAS DE OPERACIONES PANEL LATERAL------//
	// Método para diseñar botones de acciones/operaciones a realizar en el panel lateral
	private void TeclaOperacion(String NombreTecla, String infoTecla) {
		// instanciamos un JButton para crear la tecla
		JButton boton = new JButton(NombreTecla);
		// se le añade al botón el método que deriva de JComponenent, setToolTipText indica info sobre la tecla en este caso
		boton.setToolTipText(infoTecla);
		// Se le agrega fuente y tamaño
		Font nuevaFuenteTamanoMayor=new Font(boton.getFont().getName(),Font.BOLD, 26);  
		boton.setFont(nuevaFuenteTamanoMayor);
		boton.setForeground(Color.white); // color del contenido, en este caso color del número
		boton.setBackground(new java.awt.Color(153, 0, 255)); // color de fondo de la tecla lila oscuro
		Border miBorde = new LineBorder(new java.awt.Color(221, 160, 221), 10); // líneas de separación entre teclas de operaciones
		boton.setBorder(miBorde); // se establecen los bordes
		
		// Se le agrega al botón el método mouseListener para darle funcionalidad
		// Se aplica una clase anónima para el evento Mouse pudiendo así acceder a la
		// variable local boton
		boton.addMouseListener(new MouseAdapter() {

			// se sobreescribe este método para cuando se suelta el botón en el ratón
			@Override
			public void mouseReleased(MouseEvent evento) {
				JButton boton = (JButton) evento.getSource();
				pulsacion(boton.getText());
			}
		}); // aquí se cierra la clase anónima

		PanelOperaciones.add(boton); // se agrega al panel de operaciones los detalles definidos en los botones operaciones
	}

	
	// ------MÉTODOS PARA AGREGAR FUNCIONALIDAD A LA CALCULADORA------ //
	// Método que escribe en la pantalla el número pulsado o borra todo con la tecla C
	private void numeroPulsado(String numero) {
		// Si la pantalla está vacía o hay una nueva operación (marcado algún botón de operación)
		if (pantalla.getText().equals("") || nuevaOperacion) {
			//se obtiene por pantalla las teclas pulsadas (acumuladas), pero si marcamos algún botón de operaciones 
			//sirve para reiniciar la pantalla 
			pantalla.setText(numero);
		// Sino, si la tecla pulsada contiene C, el valor se reinicia a 0, sino acumula la tecla C al resto
		} else if (numero.contains("C")) {
			//el valor es igual a 0
			resultado = 0;
			// al ser el valor a 0 ponemos la pantalla vacía de nuevo (reiniciamos)
			pantalla.setText("");
			nuevaOperacion = true; // Esta variable booleana esta en true. Disponible.
		//sino, si la pantalla obtiene el texto "c" también se pone a 0
		} else if (pantalla.getText().equals("C")) {
			resultado = 0;
			//la pantalla se reinicia a 0 
			pantalla.setText("");
			nuevaOperacion = true; // Esta variable booleana esta en true. Disponible.
		// por ultimo se obtiene por pantalla el número pulsado respetando (acumulando detrás del último) los que estuvieran
		// es decir para marcar 35, primero pones 3 y luego 5, con esto obtienes 35, sin esto se machacarían entre sí.
		} else {
			pantalla.setText(pantalla.getText() + numero);
		}
		nuevaOperacion = false; // la variable booleana esta a false a no ser que se especifique lo contrario
	}

	//Metodo para calcular el resultado o para recibir el segundo valor y la operacion a realizar
	private void pulsacion(String pulsacion) {
		// si pulsamos el igual, se obtiene el resultado total
		if (pulsacion.equals("=")) {
			resultadoTotal();
		// sino, si la operacion es otra pulsacion, se obtiene el texto de la pantalla
		} else {
			operacion = pulsacion;
			// se comprueba que no hay ninguna operación pendiente
			if (resultado > 0 && !nuevaOperacion) {
				resultadoTotal(); // se obtiene el resultado
			} else {
				//sino se obtiene el resultado esperando nueva acción/operación
				resultado = new Double(pantalla.getText());
			}
		}
		nuevaOperacion = true; // la variable booleana esta a true a no ser que se especifique lo contrario
	}

	//Metodo que lleva a cabo las operaciones según la tecla pulsada.
	private void resultadoTotal() {
		// Si la tecla marcada contiene el signo "+", la operación es de suma
		if (operacion.equals("+")) {
			//es importantísimo el detalle de incluir el "+" antes de la variable pq sino no acumula
			//sino que obtiene como resultado el último valor de pantalla.
			resultado += new Double(pantalla.getText()); 		
			pantalla.setText("" + resultado);
		// Si la tecla marcada contiene el signo "-", la operación es de resta
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
			pantalla.setText("" + resultado);
		// Si la tecla marcada contiene el signo "*", la operación es de multiplicación
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
			pantalla.setText("" + resultado);
		// Si la tecla marcada contiene el signo "/", la operación es de división
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
			pantalla.setText("" + resultado);
		// Si la tecla marcada contiene el signo "^2", la operación es el cuadrado
		} else if (operacion.equals("^2")) { 
			cuadrado = resultado * resultado;
			pantalla.setText("" + cuadrado);
		}
	}

	
	// El main principal donde verdaderamente se ejecuta nuestro proyecto
	public static void main(String[] args) {

		Calculadora calculadora = new Calculadora();// Construyo un objeto calculadora
		calculadora.setVisible(true);// para que se haga visible
		calculadora.getContentPane(); // para ver todo lo que contenga el panel

	} // Se cierra el main

} // Se cierra la clase

