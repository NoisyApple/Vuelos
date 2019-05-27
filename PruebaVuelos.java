import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

class Vuelo {

    private static final String HIGHLIGHT_COLOR = "blue";
    private static final String SUBJECT_COLOR = "green";
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
                "<html>Ingrese el <span style='color: " + HIGHLIGHT_COLOR + "'>No. de Vuelo</span>:</html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE);

        String cOrigen = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Ciudad de origen</span>:</html>",
                "VUELO", JOptionPane.PLAIN_MESSAGE);

        String cDestino = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Ciudad de destino</span>:</html>",
                "VUELO", JOptionPane.PLAIN_MESSAGE);

        int hSalida = Integer.parseInt(JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Hora de salida</span>:</html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE));

        int hLlegada = Integer.parseInt(JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Hora de llegada</span>:</html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE));

        String lineaA = JOptionPane.showInputDialog(null,
                "<html>Ingrese la <span style='color: " + HIGHLIGHT_COLOR + "'>Linea aerea</span>:</html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE);

        float costo = Float.parseFloat(JOptionPane.showInputDialog(null,
                "<html>Ingrese el <span style='color: " + HIGHLIGHT_COLOR + "'>Costo</span>:</html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE));

        JOptionPane.showMessageDialog(null, "<html><span style='color: " + SUBJECT_COLOR
                + "'>REGISTRO AÑADIDO EXITOSAMENTE!</span><br><br>[Resumen del registro]<br>No. de Vuelo: <span style='color: "
                + HIGHLIGHT_COLOR + "'>" + noVuelo + "</span><br>C. Origen: <span style='color: " + HIGHLIGHT_COLOR
                + "'>" + cOrigen + "</span><br>C. Destino: <span style='color: " + HIGHLIGHT_COLOR + "'>" + cDestino
                + "</span><br>H. Salida: <span style='color: " + HIGHLIGHT_COLOR + "'>" + hSalida
                + "</span><br>H. Llegada: <span style='color: " + HIGHLIGHT_COLOR + "'>" + hLlegada
                + "</span><br>Linea aerea: <span style='color: " + HIGHLIGHT_COLOR + "'>" + lineaA
                + "</span><br>Costo: <span style='color: " + HIGHLIGHT_COLOR + "'>" + costo + "</span></html>", "VUELO",
                JOptionPane.PLAIN_MESSAGE);

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

        } catch (Exception e) {
        }

    }

    public void buscarReg() {

        try {

            archivo = new RandomAccessFile("VUELOS.txt", "rw");

            String registro = "";

            int x = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "<html>Ingrese el <span style='color: blue'>numero de registro</span> que desea encontrar:<html>", "VUELO", JOptionPane.PLAIN_MESSAGE));

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
                        "<html><span style='color: red'>ERROR!</span> No se encontro el registro especificado.<html>", "VUELO", JOptionPane.ERROR_MESSAGE);
            } else {
                String html = "<html><font size='6'><p align='center' style='color: purple'>REGISTRO ENCONTRADO</p><table><tr style='color: blue'><th>No. Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Hora Salida</th><th>Hora Llegada</th><th>Linea Aerea</th><th>Costo</th></tr><tr><td>";

                for (int j = 0; j < registro.length(); j++) {

                    if (registro.charAt(j) == '\t')
                        html += "</td><td>";

                    html += registro.charAt(j);

                }

                html = html.substring(0, html.length() - 4);

                html += "</tr>";

                JOptionPane.showMessageDialog(null, html, "VUELO", JOptionPane.PLAIN_MESSAGE);
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

            JOptionPane.showMessageDialog(null, cadena, "VUELO", JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}

class PruebaVuelos {

    public static void main(String[] args) {

        // try {
        // RandomAccessFile r = new RandomAccessFile("VUELOS.txt", "rw");

        // int c;

        // while ((c = r.read()) != -1) {

        // System.out.println(c);

        // }
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // System.out.println("\n".getBytes()[0]);

        // TODO: Get the first line identified as an String found before an space (Or an
        // special character)

        RegistroDeVuelo regV = new RegistroDeVuelo();
        // regV.añadirReg(Vuelo.crearVuelo());
        // regV.mostrarReg();
        regV.buscarReg();

    }

}
