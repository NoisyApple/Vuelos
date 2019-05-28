import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

class Vuelo {

    public static final String HIGHLIGHT_COLOR = "blue";
    public static final String SUBJECT_COLOR = "green";
    private String noVuelo;
    private String cOrigen;
    private String cDestino;
    private int hSalida;
    private int hLlegada;
    private String lineaA;
    private float costo;

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

    public static Vuelo crearVuelo() {

        String noVuelo = JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: " + HIGHLIGHT_COLOR + "'>No. de Vuelo</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        String cOrigen = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Ciudad de origen</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        String cDestino = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Ciudad de destino</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        int hSalida = Integer.parseInt(JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Hora de salida</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE));

        int hLlegada = Integer.parseInt(JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Hora de llegada</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE));

        String lineaA = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Linea aerea</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

        float costo = Float.parseFloat(JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: " + HIGHLIGHT_COLOR + "'>Costo</span>:</html>",
                "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE));

        return new Vuelo(noVuelo, cOrigen, cDestino, hSalida, hLlegada, lineaA, costo);

    }

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

class RegistroDeVuelo {

    private RandomAccessFile archivo;

    public void añadirReg(Vuelo v) {

        try {
            archivo = new RandomAccessFile("VUELOS.txt", "rw");

            String registro = "";

            int c;

            archivo.seek(0);

            while ((c = archivo.read()) != -1) {
                if (c == 9 || c == 10) {

                    if (v.getNoVuelo().equalsIgnoreCase(registro)) {
                        registro += "\t";
                        break;
                    } else {
                        registro = "";
                    }

                } else {
                    registro += (char) c;
                }
            }

            System.out.println(registro);

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

        } catch (Exception e) {
        }

    }

    public void buscarReg() {

        try {

            archivo = new RandomAccessFile("VUELOS.txt", "rw");

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

                JOptionPane.showMessageDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void mostrarReg() {

        try {
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

            System.out.println(cadena);

            JOptionPane.showMessageDialog(null, cadena, "AEROPUERTO INTERNACIONAL DEL BAJIO",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void eliminarReg() {

        try {

            archivo = new RandomAccessFile("VUELOS.txt", "rw");

            String registro = "";

            String x = JOptionPane.showInputDialog(null,
                    "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea <span style='color: red'>eliminar</span>:<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

            int c;
            archivo.seek(0);

            while ((c = archivo.read()) != -1) {
                if (c == 9 || c == 10) {

                    if (x.equalsIgnoreCase(registro)) {
                        registro += "\t";
                        break;
                    } else {
                        registro = "";
                    }

                } else {
                    registro += (char) c;
                }
            }

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    break;

                registro += (char) c;
            }

            String text = "";

            archivo.seek(0);

            while ((c = archivo.read()) != -1) {

                text += (char) c;

            }

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

                    registro += "\n";

                    text = text.replace(registro, "");

                    archivo.setLength(0);
                    archivo.seek(0);
                    archivo.write(text.getBytes());

                    JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.",
                            "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.INFORMATION_MESSAGE);

                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void buscarVuelo() {

        try {

            archivo = new RandomAccessFile("VUELOS.txt", "rw");

            String registro = "";

            String x = JOptionPane.showInputDialog(null,
                    "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea encontrar:<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

            int c;
            archivo.seek(0);

            while ((c = archivo.read()) != -1) {
                if (c == 9 || c == 10) {

                    if (x.equalsIgnoreCase(registro)) {
                        registro += "\t";
                        break;
                    } else {
                        registro = "";
                    }

                } else {
                    registro += (char) c;
                }
            }

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    break;

                registro += (char) c;
            }

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

                JOptionPane.showMessageDialog(null, html, "AEROPUERTO INTERNACIONAL DEL BAJIO",
                        JOptionPane.PLAIN_MESSAGE);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void modificarReg() {

        try {

            archivo = new RandomAccessFile("VUELOS.txt", "rw");

            String registro = "";

            String x = JOptionPane.showInputDialog(null,
                    "<html>Ingrese el <span style='color: blue'>numero de vuelo</span> que desea encontrar:<html>",
                    "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);

            int c;
            archivo.seek(0);

            while ((c = archivo.read()) != -1) {
                if (c == 9 || c == 10) {

                    if (x.equalsIgnoreCase(registro)) {
                        registro += "\t";
                        break;
                    } else {
                        registro = "";
                    }

                } else {
                    registro += (char) c;
                }
            }

            while ((c = archivo.read()) != -1) {
                if (c == 10)
                    break;

                registro += (char) c;
            }

            if (registro == "") {
                JOptionPane.showMessageDialog(null,
                        "<html><span style='color: red'>ERROR!</span> No se encontro el numero de vuelo \"" + x
                                + "\".<html>",
                        "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] titulos = { "Ciudad Origen", "Ciudad Destino", "Hora de Salida", "Hora de Llegada",
                        "Linea Aerea", "Costo" };

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

                    registro += "\n";

                    String text = "";

                    archivo.seek(0);

                    while ((c = archivo.read()) != -1) {
                        text += (char) c;
                    }

                    text = text.replace(registro, newReg);

                    archivo.setLength(0);
                    archivo.seek(0);
                    archivo.write(text.getBytes());

                    JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.",
                            "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.INFORMATION_MESSAGE);

                    // TODO Terminar esta parte (Cargar datos del arreglo datos[] a el archivo)

                } else {
                    JOptionPane.showMessageDialog(null,
                            "<html><span style='color: red'>ERROR!</span>Opcion invalida.<html>",
                            "AEROPUERTO INTERNACIONAL DEL BAJIO", JOptionPane.PLAIN_MESSAGE);
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}

class PruebaVuelos {

    public static void main(String[] args) {

        int opt;

        RegistroDeVuelo regV = new RegistroDeVuelo();

        while (true) {

            opt = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "<html><font size='4'><p align='center' style='color: #FF2B8A'>AEROPUERTO INTERNACIONAL DEL BAJIO</p></font><p align='center' style='color: #333333; font-size: 14'>Ingrese el numero de la opcion <br>que desea realizar:</p><br><table align='center' style='color: #333333; font-size: 10px'><tr><th>[<span style='color: #345995'>1</span>]</th><th align='left'>Añadir un registro de vuelo.</th></tr><tr><th>[<span style='color: #345995'>2</span>]</th><th align='left'>Buscar por numero de registro.</th></tr><tr><th>[<span style='color: #345995'>3</span>]</th><th align='left'>Buscar por numero de vuelo.</th></tr><tr><th>[<span style='color: #345995'>4</span>]</th><th align='left'>Eliminar un registro de vuelo.</th></tr><tr><th>[<span style='color: #345995'>5</span>]</th><th align='left'>Modificar un registro de vuelo.</th></tr><tr><th>[<span style='color: #345995'>6</span>]</th><th align='left'>Mostrar listado de vuelos.</th></tr><tr><th>[<span style='color: #345995'>7</span>]</th><th align='left'>Salir.</th></tr></table><br><html>",
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

            if (JOptionPane.showConfirmDialog(null, "Desea realizar otra accion?", "AEROPUERTO INTERNACIONAL DEL BAJIO",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                break;
        }

        // regV.añadirReg(Vuelo.crearVuelo());
        // regV.mostrarReg();
        // regV.buscarReg();
        // regV.buscarVuelo();
        // regV.eliminarReg();
        // regV.modificarReg();

    }

}
