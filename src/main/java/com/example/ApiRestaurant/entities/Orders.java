package com.example.ApiRestaurant.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pedidos")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long orderId;

     private LocalDate fechaComprado;

     private double total;
     
     private LocalTime horaDePedido;
     
     private LocalTime horaDeEntregado;
     
  
     @Transient
     private LocalTime tiempoTranscurrido;

    @Enumerated(EnumType.STRING)
     private OrderStatus status;
     
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "clienteId" )
     private Cliente cliente;

    // @OneToMany
	// private List<CartItem> ordercartItems = new ArrayList<>();
    @OneToMany
    private List<ItemProduct> ordercartItems = new ArrayList<>();


    public LocalDate getFechaComprado() {
        return fechaComprado;
    }

    public void setFechaComprado(LocalDate fechaComprado) {
        this.fechaComprado = fechaComprado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalTime getHoraDePedido() {
        return horaDePedido;
    }

    public void setHoraDePedido(LocalTime horaDePedido) {
        this.horaDePedido = horaDePedido;
    }

    public LocalTime getHoraDeEntregado() {
        return horaDeEntregado;
    }

    public void setHoraDeEntregado(LocalTime horaDeEntregado) {
        this.horaDeEntregado = horaDeEntregado;
    }

    public long getTiempoTranscurrido() {
        return  ChronoUnit.MINUTES.between(this.horaDePedido, this.horaDeEntregado);
    }

    public void setTiempoTranscurrido(LocalTime tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<ItemProduct> getOrdercartItems() {
        return ordercartItems;
    }

    public void setOrdercartItems(List<ItemProduct> ordercartItems) {
        this.ordercartItems = ordercartItems;
    }

   

    
}
