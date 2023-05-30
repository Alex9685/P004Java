package com.example.p004java;

import android.widget.TextView;

public class ReciboNomina {

        private int numRecibo;
        private String nombre;
        private int horasTrabNormal;
        private int horasTrabExtras;
        private int puesto;

    public ReciboNomina(int numRecibo, String nombre, int horasTrabNormal, int horasTrabExtras, int puesto) {
        this.numRecibo = numRecibo;
        this.nombre = nombre;
        this.horasTrabNormal = horasTrabNormal;
        this.horasTrabExtras = horasTrabExtras;
        this.puesto = puesto;
    }

        public double calcularSubtotal() {
            double pagoBase = 200;

            double pagoPorHora;

            if (puesto == 1) {
                pagoPorHora = pagoBase * 1.2; // 20% de incremento
            } else if (puesto == 2) {
                pagoPorHora = pagoBase * 1.5; // 50% de incremento
            } else if (puesto == 3) {
                pagoPorHora = pagoBase * 2.0; // 100% de incremento
            } else {
                pagoPorHora = pagoBase;
            }

            double subtotal = (horasTrabNormal * pagoPorHora) + (horasTrabExtras * pagoPorHora * 2);
            return subtotal;
        }

        public double calcularImpuesto() {
            double subtotal = calcularSubtotal();
            double impuesto = subtotal * 0.16; // 16% del subtotal
            return impuesto;
        }

        public double calcularTotal() {
            double subtotal = calcularSubtotal();
            double impuesto = calcularImpuesto();
            double total = subtotal - impuesto;
            return total;
        }

        // Métodos getter y setter para los atributos





        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getHorasTrabNormal() {
            return horasTrabNormal;
        }

        public void setHorasTrabNormal(int horasTrabNormal) {
            this.horasTrabNormal = horasTrabNormal;
        }

        public int getHorasTrabExtras() {
            return horasTrabExtras;
        }

        public void setHorasTrabExtras(int horasTrabExtras) {
            this.horasTrabExtras = horasTrabExtras;
        }

        public int getPuesto() {
            return puesto;
        }

        public void setPuesto(int puesto) {
            this.puesto = puesto;
        }
    }








