package br.com.uol.entities;


import java.util.Date;

public record ApiErrorMessage(int statusCode, Date timestamp, String message, String description) {

}
