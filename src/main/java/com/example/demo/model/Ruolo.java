package com.example.demo.model;

// L'Enum è un tipo di dato speciale che si usa solitamente per rappresentare un insieme di costanti.
// E' un modo più sicuro per usare costanti di tipo String o int perchè forniscono valori limitati.
// Possono contenere campi, metodi e costruttori, consentendo di raggruppare dati e comportamenti correlati.
// Questo li rende strumenti potenti per modellare concetti complessi in modo semplice ed espressivo.
// Possono essere utilizzate per implementare il pattern Strategy in modo semplice.
// Ogni costante enum può rappresentare una diversa strategia, e l'enum può fornire un metodo astratto implementato diversamente da ciascuna delle sue costanti.
public enum Ruolo {

    USER,
    ADMIN,

}
