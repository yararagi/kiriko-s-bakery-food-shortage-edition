package menu.model;
/**
 *  l'oggetto Stato viene usato per conservare le varie modalite che il programma può assumere, tali sono:
 *  MENU: dove l'utente può decidere dove andare
 *  GIOCA: dove l'utente può giocare al gioco kiriko's bakery food shortage edition
 *  RISULTATI: dove l'utente può vedere i risultati dei precedenti gioci
 *  ESCI: dove l'utente può decidere di chiudere l'aplicazione
 */
public enum Stato {
    MENU,
    GIOCA,
    RISULTATI,
    ESCI
}
