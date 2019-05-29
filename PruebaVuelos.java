import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// 

// NOTA: El proyecto no cuenta con archivos de tipo .class debido al editor que 
// se utilizo para su desarrollo.

// ERRORES CONOCIDOS: 
// ***Cuando se crea un registro nuevo, este se almacena sin un caracter necesario 
// para que el metodo eliminarReg() lo ubique y lo elimine, por lo que es imposible 
// eliminar todos los registros, ya que siempre quedara el ultimo.
// ***El programa ocasionalmente falla, ocasionando un error que ya esta manejado
// en el codigo, asi que simplemente emergera una ventana de dialogo, si esto sucede 
// se recomienda intentar realizar la misma accion nuevamente.

// Clase principal de prueba PruebaVuelos: crea una instancia de tipo
// RegistroDeVuelo en la que itera por medio de un menu entre sus funciones en
// base a las decisiones del usuario.
class PruebaVuelos {

    public static void main(String[] args) {

        try {
            int opt;

            RegistroDeVuelo regV = new RegistroDeVuelo("VUELOS.txt");

            while (true) {

                opt = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "<html><font size='4'><p align='center' style='color: #FF2B8A'>AEROPUERTO "
                                + "INTERNACIONAL DEL BAJIO</p></font><p align='center' style='color: #333333; "
                                + "font-size: 14'>Ingrese el numero de la opcion <br>que desea "
                                + "realizar:</p><br><table align='center' style='color: #333333; "
                                + "font-size:10px'><tr><th>[<span style='color: "
                                + "#345995'>1</span>]</th><thalign='left'>Añadir un registro de "
                                + "vuelo.</th></tr><tr><th>[<span style='color: #345995'>2</span>]</th><th "
                                + "align='left'>Buscar por numero de registro.</th></tr><tr><th>[<span "
                                + "style='color: #345995'>3</span>]</th><th align='left'>Buscar por numero de "
                                + "vuelo.</th></tr><tr><th>[<span style='color: #345995'>4</span>]</th><th "
                                + "align='left'>Eliminar un registro de vuelo.</th></tr><tr><th>[<span "
                                + "style='color: #345995'>5</span>]</th><th align='left'>Modificar un registro "
                                + "de vuelo.</th></tr><tr><th>[<span style='color: #345995'>6</span>]</th><th "
                                + "align='left'>Mostrar listado de vuelos.</th></tr><tr><th>[<span style='color: "
                                + "#345995'>7</span>]</th><th align='left'>Salir.</th></tr></table><br><html>",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE));

                switch (opt) {
                case 1:
                    regV.añadirReg(Vuelo.crearVuelo());
                    break;
                case 2:
                    regV.buscarReg();
                    break;
                case 3:
                    regV.buscarVuelo();
                    break;
                case 4:
                    regV.eliminarReg();
                    break;
                case 5:
                    regV.modificarReg();
                    break;
                case 6:
                    regV.mostrarReg();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: red'>ERROR!</span> Opcion invalida.<html>",
                            "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "<html><span style='color: red'>ERROR!</span> No se pudo crear o leer el " + "archivo.<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);

        }

    }

}

// Clase CapturaDeDatosDeVuelo: Modela una ventana en la que se capturaran los
// datos de un vuelo.
class CapturadoraDeDatosDeVuelo extends JPanel {

    private JPanel panelPrincipal, panelInputs1, panelInputs2, panelInputs3, panelInputs4;
    private JLabel lblTitulo, lblInstruccion, lblNoVuelo, lblCosto, lblCOrigen, lblCDestino, lblHSalida, lblHLlegada,
            lblLineaA, lblMensaje;
    private JTextField txtNoVuelo, txtCosto, txtCOrigen, txtCDestino, txtHSalida, txtHLlegada, txtLineaA;

    public CapturadoraDeDatosDeVuelo() {

        setLayout(new FlowLayout());

        panelPrincipal = new JPanel(new GridLayout(8, 1));

        lblTitulo = new JLabel("AEROPUERTO INTERNACIONAL DEL BAJIO", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(0xFF2B8A));
        lblTitulo.setFont(new Font("", Font.BOLD, 16));

        lblInstruccion = new JLabel("Ingrese los datos del vuelo:", SwingConstants.CENTER);
        lblInstruccion.setForeground(new Color(0x333333));
        lblInstruccion.setFont(new Font("", Font.BOLD, 14));

        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(lblInstruccion);

        panelInputs1 = new JPanel(new FlowLayout());

        lblNoVuelo = new JLabel("No. de Vuelo:");

        txtNoVuelo = new JTextField(10);

        txtNoVuelo.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {

                try {
                    RegistroDeVuelo regV = new RegistroDeVuelo("VUELOS.txt");

                    String registro = regV.obtenerLinea(txtNoVuelo.getText());

                    if (registro != "") {
                        lblMensaje.setVisible(true);
                    } else {
                        lblMensaje.setVisible(false);
                    }

                    if (txtNoVuelo.getText().length() == 0)
                        lblMensaje.setVisible(false);

                } catch (Exception ioE) {
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {

                try {
                    RegistroDeVuelo regV = new RegistroDeVuelo("VUELOS.txt");

                    String registro = regV.obtenerLinea(txtNoVuelo.getText());

                    if (registro != "") {
                        lblMensaje.setVisible(true);
                    } else {
                        lblMensaje.setVisible(false);
                    }

                    if (txtNoVuelo.getText().length() == 0)
                        lblMensaje.setVisible(false);

                } catch (Exception ioE) {
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                try {
                    RegistroDeVuelo regV = new RegistroDeVuelo("VUELOS.txt");

                    String registro = regV.obtenerLinea(txtNoVuelo.getText());

                    if (registro != "") {
                        lblMensaje.setVisible(true);
                    } else {
                        lblMensaje.setVisible(false);
                    }

                    if (txtNoVuelo.getText().length() == 0)
                        lblMensaje.setVisible(false);

                } catch (Exception ioE) {
                }

            }
        });

        lblCosto = new JLabel("Costo:");
        lblCosto.setBorder(new EmptyBorder(0, 15, 0, 0));

        txtCosto = new JTextField(16);

        panelInputs1.add(lblNoVuelo);
        panelInputs1.add(txtNoVuelo);
        panelInputs1.add(lblCosto);
        panelInputs1.add(txtCosto);

        panelPrincipal.add(panelInputs1);

        panelInputs2 = new JPanel(new FlowLayout());

        lblCOrigen = new JLabel("Ciudad Origen:");

        txtCOrigen = new JTextField(9);
        txtCOrigen.setBorder(BorderFactory.createCompoundBorder(txtCOrigen.getBorder(),
                BorderFactory.createEmptyBorder(0, 0, 0, 4)));

        lblCDestino = new JLabel("Ciudad Destino:");
        lblCDestino.setBorder(new EmptyBorder(0, 15, 0, 0));

        txtCDestino = new JTextField(11);

        lblCDestino.setBackground(Color.CYAN);

        panelInputs2.add(lblCOrigen);
        panelInputs2.add(txtCOrigen);
        panelInputs2.add(lblCDestino);
        panelInputs2.add(txtCDestino);

        panelPrincipal.add(panelInputs2);

        panelInputs3 = new JPanel(new FlowLayout());

        lblHSalida = new JLabel("H. Salida:");

        txtHSalida = new JTextField(11);
        txtHSalida.setBorder(BorderFactory.createCompoundBorder(txtHSalida.getBorder(),
                BorderFactory.createEmptyBorder(0, 0, 0, 6)));

        lblHLlegada = new JLabel("H. Llegada:");
        lblHLlegada.setBorder(new EmptyBorder(0, 15, 0, 0));

        txtHLlegada = new JTextField(14);

        panelInputs3.add(lblHSalida);
        panelInputs3.add(txtHSalida);
        panelInputs3.add(lblHLlegada);
        panelInputs3.add(txtHLlegada);

        panelPrincipal.add(panelInputs3);

        panelInputs4 = new JPanel(new FlowLayout());

        lblLineaA = new JLabel("Linea Aerea:");

        txtLineaA = new JTextField(32);
        txtLineaA.setBorder(
                BorderFactory.createCompoundBorder(txtLineaA.getBorder(), BorderFactory.createEmptyBorder(0, 0, 0, 2)));

        panelInputs4.add(lblLineaA);
        panelInputs4.add(txtLineaA);

        panelPrincipal.add(panelInputs4);

        lblMensaje = new JLabel("Ya existe el numero de vuelo ingresado.", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.red);
        lblMensaje.setFont(new Font("", Font.PLAIN, 12));

        lblMensaje.setVisible(false);

        panelPrincipal.add(lblMensaje);

        add(panelPrincipal);

        setSize(500, 350);
        setVisible(true);

    }

    // Crea una ventana de dialogo con JOptionPane, insertando en ella el
    // panelPrincipal construido en esta clase, este panel permite al usuario
    // capturar los datos de un vuelo, siempre y cuando se cumplan los requisitos,
    // el metodo devolvera un objeto de tipo Vuelo con los datos capturados, de lo
    // contrario regresara null.
    public Vuelo mostarPanelDeDatos() {

        int resultado = JOptionPane.showConfirmDialog(null, panelPrincipal, "", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {

            System.out.println(txtNoVuelo.getText().length() + "\n" + txtCOrigen.getText().length() + "\n"
                    + txtCDestino.getText().length() + "\n" + txtHSalida.getText().length() + "\n"
                    + txtHLlegada.getText().length() + "\n" + txtLineaA.getText().length() + "\n"
                    + txtCosto.getText().length());

            if (txtNoVuelo.getText().length() == 0 || txtCOrigen.getText().length() == 0
                    || txtCDestino.getText().length() == 0 || txtHSalida.getText().length() == 0
                    || txtHLlegada.getText().length() == 0 || txtLineaA.getText().length() == 0
                    || txtCosto.getText().length() == 0) {

                JOptionPane.showMessageDialog(null,
                        "<html><span style='color: red'>ERROR !</span> No se capturaron todos los datos necesarios.<html>",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);

                return null;

            } else {

                if (!(lblMensaje.isVisible())) {

                    System.out.println("Exito!");

                    return new Vuelo(txtNoVuelo.getText(), txtCOrigen.getText(), txtCDestino.getText(),
                            Integer.parseInt(txtHSalida.getText()), Integer.parseInt(txtHLlegada.getText()),
                            txtLineaA.getText(), Float.parseFloat(txtCosto.getText()));

                } else {

                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: red'>ERROR !</span> El numero de vuelo especificado ya esta registrado.<html>",
                            "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);

                    return null;

                }

            }

        } else {

            return null;
        }

    }

}

// Clase Vuelo: Recopila los datos generales de un vuelo.
class Vuelo {

    public static final String HIGHLIGHT_COLOR = "blue"; // Constante que controla el color de resaltado de los menus.
    public static final String SUBJECT_COLOR = "green"; // Constante que controla el color del tema de los menus.
    private String noVuelo;
    private String cOrigen;
    private String cDestino;
    private int hSalida;
    private int hLlegada;
    private String lineaA;
    private float costo;

    // Constructor de la clase Vuelo
    public Vuelo(String noVuelo, String cOrigen, String cDestino, int hSalida, int hLlegada, String lineaA,
            float costo) {
        this.noVuelo = noVuelo;
        this.cOrigen = cOrigen;
        this.cDestino = cDestino;
        this.hSalida = hSalida;
        this.hLlegada = hLlegada;
        this.lineaA = lineaA;
        this.costo = costo;
    }

    // Devuelve un objeto de tipo Vuelo con los datos capturados dentro de este
    // metodo a travez de un objeto de tipo CapturaDeDatosDeVuelo.
    public static Vuelo crearVuelo() {

        CapturadoraDeDatosDeVuelo cap = new CapturadoraDeDatosDeVuelo();

        return cap.mostarPanelDeDatos();

    }

    // Setters y getters de la clase para uso general.
    public void setNoVuelo(String noVuelo) {
        this.noVuelo = noVuelo;
    }

    public String getNoVuelo() {
        return noVuelo;
    }

    public void setCOrigen(String cOrigen) {
        this.cOrigen = cOrigen;
    }

    public String getCOrigen() {
        return cOrigen;
    }

    public void setCDestino(String cDestino) {
        this.cDestino = cDestino;
    }

    public String getCDestino() {
        return cDestino;
    }

    public void setHSalida(int hSalida) {
        this.hSalida = hSalida;
    }

    public int getHSalida() {
        return hSalida;
    }

    public void setHLlegada(int hLlegada) {
        this.hLlegada = hLlegada;
    }

    public int getHLlegada() {
        return hLlegada;
    }

    public void setLineaA(String lineaA) {
        this.lineaA = lineaA;
    }

    public String getLineaA() {
        return lineaA;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getCosto() {
        return costo;
    }

}

// Clase RegistroDeVuelo: Modela un registro de vuelo que almacena datos en un
// archivo de acceso aleatorio a traves de objetos de tipo Vuelo al igual que
// implementa funcionalidades para los datos capturados en el.
class RegistroDeVuelo {

    private RandomAccessFile archivo;
    public static final int REEMPLAZAR = 1;
    public static final int ELIMINAR = 2;

    // Constructor de la clase RegistroDeVuelo.
    public RegistroDeVuelo(String nombreArchivo) throws IOException {

        this.archivo = new RandomAccessFile(nombreArchivo, "rw");

    }

    // Pide como parametro un objeto de tipo vuelo y escribe los datos obtenidos de
    // este en un registro dentro del archivo. En caso de que ya existiera un
    // registro con el numero de vuelo especificado se descartaran los demas datos
    // capturados y no se sobreescribira el archivo.
    public void añadirReg(Vuelo v) throws IOException {

        if (v != null) {
            String registro = obtenerLinea(v.getNoVuelo());

            if (registro == "") {
                archivo.seek((int) archivo.length());

                if (archivo.length() != 0)
                    archivo.write("\n".getBytes());

                archivo.write((v.getNoVuelo() + "\t").getBytes());
                archivo.write((v.getCOrigen() + "\t").getBytes());
                archivo.write((v.getCDestino() + "\t").getBytes());
                archivo.write((v.getHSalida() + "\t").getBytes());
                archivo.write((v.getHLlegada() + "\t").getBytes());
                archivo.write((v.getLineaA() + "\t").getBytes());
                archivo.write((v.getCosto() + "\t").getBytes());

                JOptionPane.showMessageDialog(null, "<html><span style='color: " + Vuelo.SUBJECT_COLOR
                        + "'>REGISTRO AÑADIDO EXITOSAMENTE!</span><br><br>[Resumen del registro]<br>No. de Vuelo: <span style='color: "
                        + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getNoVuelo() + "</span><br>C. Origen: <span style='color: "
                        + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getCOrigen() + "</span><br>C. Destino: <span style='color: "
                        + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getCDestino() + "</span><br>H. Salida: <span style='color: "
                        + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getHSalida() + "</span><br>H. Llegada: <span style='color: "
                        + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getHLlegada()
                        + "</span><br>Linea aerea: <span style='color: " + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getLineaA()
                        + "</span><br>Costo: <span style='color: " + Vuelo.HIGHLIGHT_COLOR + "'>" + v.getCosto()
                        + "</span></html>", "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null,
                        "<html><span style='color: red'>ERROR !</span> Ya existe un vuelo con el mismo numero.<br>Ingrese un numero que no este registrado.<html>",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    // Busca el registro indicado en base a un numero entero, en caso de que el
    // registro que se busque sea el numero 1, el puntero del archivo se posicionara
    // al principio de este y almacenara todos los caracteres siguientes hasta
    // encontrarse con un caracter de salto de linea (especificado en el codigo con
    // valor en bytes de 10). Si se desea buscar un registro mayor a 1, el algoritmo
    // ira contando todos los caracteres de salto de linea y cuando llegue al
    // indicado almacenara e imprimira el registro encontrado en la posicion donde
    // este se encuentre hasta nuevamente encontrar otro caracter de salto de linea.
    public void buscarReg() throws IOException {

        String registro = "";

        int x = Integer.parseInt(JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: blue'>numero de registro</span> que desea encontrar:<html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE));

        int c;
        archivo.seek(0);

        if (x == 1) {

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    break;

                registro += (char) c;
            }

        } else if (x > 1) {
            int regCont = 1;

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    regCont++;

                if (regCont == x)
                    break;
            }

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    break;

                registro += (char) c;
            }

        }

        if (registro == "") {
            JOptionPane.showMessageDialog(null,
                    "<html><span style='color: red'>ERROR!</span> No se encontro el registro especificado.<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
        } else {
            String html = "<html><font size='6'><p align='center' style='color: purple'>REGISTRO ENCONTRADO</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Hora Salida</th><th>Hora Llegada</th><th>Linea Aerea</th><th>Costo</th></tr><tr><td>";

            for (int j = 0; j < registro.length(); j++) {

                if (registro.charAt(j) == '\t')
                    html += "</td><td>";

                html += registro.charAt(j);

            }

            html = html.substring(0, html.length() - 4);

            html += "</tr>";

            JOptionPane.showMessageDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);
        }

    }

    // Recorre todo el archivo caracter a caracter identificando si se lee un
    // caracter de tipo tabulacion (identificado como 9 en bytes), una vez que
    // reconoce un dato, lo concatena a un String con formato html y continua
    // buscando mas datos hasta que se llega al final del archivo y concatena el
    // final del codigo html. Es entonces cuando lo imprime.
    public void mostrarReg() throws IOException {

        archivo = new RandomAccessFile("VUELOS.txt", "rw");

        int c = 0;

        String cadena = "<html><font size='6'><p align='center' style='color: purple'>LISTADO DE VUELOS</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Hora Salida</th><th>Hora Llegada</th><th>Linea Aerea</th><th>Costo</th></tr>";

        String temp = "";

        while (true) {

            int i = 0;

            cadena += "<tr>";

            while (i < 7) {

                cadena += "<td>";

                while ((c = archivo.read()) != -1) {
                    if (c == 9) {
                        cadena += temp;
                        temp = "";
                        break;
                    } else if (c == 10) {
                    } else {
                        temp += ((char) c);
                    }
                }

                cadena += "</td>";
                i++;

            }

            cadena += "</tr>";

            if (c == -1)
                break;

        }

        archivo.close();

        cadena = cadena.substring(0, cadena.length() - 72) + "</table><html>";

        JOptionPane.showMessageDialog(null, cadena, "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

    }

    // Busca una linea que corresponda a el valor especificado que se desea buscar,
    // una vez que se encuentra esta linea se reemplaza por una cadena de texto
    // vacia y se actualiza el contenido del archivo.
    public void eliminarReg() throws IOException {

        String x = JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea <span style='color: red'>eliminar</span>:<html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        String registro = obtenerLinea(x);

        if (registro == "") {
            JOptionPane.showMessageDialog(null,
                    "<html><span style='color: red'>ERROR!</span> No se encontro el numero de vuelo \"" + x
                            + "\".<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
        } else {

            String html = "<html><font size='4'><p align='center' style='color: orange'>Esta seguro que desea eliminar este vuelo?</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Hora Salida</th><th>Hora Llegada</th><th>Linea Aerea</th><th>Costo</th></tr><tr><td>";

            for (int j = 0; j < registro.length(); j++) {

                if (registro.charAt(j) == '\t')
                    html += "</td><td>";

                html += registro.charAt(j);

            }

            html = html.substring(0, html.length() - 4);

            html += "</tr>";

            if (JOptionPane.showConfirmDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                reemplazarRegistro(registro, "", RegistroDeVuelo.ELIMINAR);

                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }

    // Busca una linea de texto en base a el numero de vuelo indicado, recorre todo
    // el archivo evaluando cada dato que se encuentra, si uno de los datos coincide
    // con el numero de vuelo, el algoritmo dejara de reconocer los datos y
    // simplemente almacenara toda la linea siguiente (hasta un salto de linea) y la
    // imprimira.
    public void buscarVuelo() throws IOException {

        String x = JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea encontrar:<html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        String registro = obtenerLinea(x);

        if (registro == "") {
            JOptionPane.showMessageDialog(null,
                    "<html><span style='color: red'>ERROR!</span> No se encontro el numero de vuelo \"" + x
                            + "\".<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
        } else {
            String html = "<html><font size='6'><p align='center' style='color: purple'>VUELO ENCONTRADO</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Hora Salida</th><th>Hora Llegada</th><th>Linea Aerea</th><th>Costo</th></tr><tr><td>";

            for (int j = 0; j < registro.length(); j++) {

                if (registro.charAt(j) == '\t')
                    html += "</td><td>";

                html += registro.charAt(j);

            }

            html = html.substring(0, html.length() - 4);

            html += "</tr>";

            JOptionPane.showMessageDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);
        }

    }

    public void modificarReg() throws IOException {

        String x = JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea encontrar:<html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        String registro = obtenerLinea(x);

        if (registro == "") {
            JOptionPane.showMessageDialog(null,
                    "<html><span style='color: red'>ERROR!</span> No se encontro el numero de vuelo \"" + x
                            + "\".<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] titulos = { "Ciudad Origen", "Ciudad Destino", "Hora de Salida", "Hora de Llegada", "Linea Aerea",
                    "Costo" };

            String[] datos = registro.split("\t");

            String html = "<html><font size='3'><p align='center' style='color: purple'>Elija el numero del dato que quiere modificar:</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen(1)</th><th>Ciudad Destino(2)</th><th>Hora Salida(3)</th><th>Hora Llegada(4)</th><th>Linea Aerea(5)</th><th>Costo(6)</th></tr><tr><td>";

            for (int j = 0; j < registro.length(); j++) {

                if (registro.charAt(j) == '\t')
                    html += "</td><td>";

                html += registro.charAt(j);

            }

            html = html.substring(0, html.length() - 4);

            html += "</tr>";

            int opt = Integer.parseInt(JOptionPane.showInputDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO",
                    JOptionPane.PLAIN_MESSAGE));

            if (opt > 0 && opt < 7) {
                datos[opt] = JOptionPane
                        .showInputDialog(null,
                                "<html>Ingrese el nuevo valor para: <span style='color: blue'>" + titulos[opt - 1]
                                        + "</span><html>",
                                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

                String newReg = "";

                for (String s : datos) {
                    newReg += s + "\t";
                }

                newReg = newReg.substring(0, newReg.length() - 1);

                reemplazarRegistro(registro, newReg, RegistroDeVuelo.REEMPLAZAR);

                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "<html><span style='color: red'>ERROR!</span>Opcion invalida.<html>",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    // Copia el contenido del archivo en una variable temporal, en esta se busca un
    // texto especificado y se remplaza por otro, se envia un parametro para definir
    // el modo de reemplazo, si se usa RegistroDeVuelo.REEMPLAZAR el texto
    // modificado se adaptara a una nueva linea de remplazo, de lo contrario, si se
    // usa RegistroDeVuelo.ELIMINAR y un texto nuevo en blanco, se adaptara el
    // algoritmo para dejar una linea en blanco en el lugar que corresponde al texto
    // de objetivo.

    public void reemplazarRegistro(String objetivo, String textoNuevo, int modo) throws IOException {

        String textoOriginal = "";

        int c;

        archivo.seek(0);

        while ((c = archivo.read()) != -1) {
            textoOriginal += (char) c;
        }

        switch (modo) {
        case 1:
            textoNuevo = textoOriginal.replace(objetivo, textoNuevo + "\t");
            break;
        case 2:
            textoNuevo = textoOriginal.replace(objetivo + "\n", textoNuevo);
            break;
        }

        archivo.setLength(0);
        archivo.seek(0);
        archivo.write(textoNuevo.getBytes());

    }

    // Busca en el archivo una porcion de texto que concuerde con el proporcionado
    // como parametro y regresa la linea entera donde se encuentra este texto, en
    // caso de que no se encuentre una coincidencia el metodo devuelve un String
    // vacio.

    public String obtenerLinea(String textoABuscar) throws IOException {

        String linea = "";

        int c;
        archivo.seek(0);

        while ((c = archivo.read()) != -1) {
            if (c == 9 || c == 10) {

                if (textoABuscar.equalsIgnoreCase(linea)) {
                    linea += "\t";
                    break;
                } else {
                    linea = "";
                }

            } else {
                linea += (char) c;
            }
        }

        while ((c = archivo.read()) != -1) {
            if (c == 10)
                break;

            linea += (char) c;
        }

        return linea;

    }

}
