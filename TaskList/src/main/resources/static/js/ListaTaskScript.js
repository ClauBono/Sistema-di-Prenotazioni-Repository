$(document).ready(function () {

    // Inizializzazione della tabella principale
    let table = $('#listaTask').DataTable({
        "ajax": {
            url: "/api/taskList", // Endpoint per ottenere i dati dalla tua API
            dataSrc: "" // Campo che contiene i dati nell'oggetto JSON restituito
        },
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Italian.json"
        },
        "columns": [
            {"data": "id"}, // Colonna per l'ID
            {"data": "nome"}, // Colonna per il nome
            {"data": "descrizione"}, // Colonna per la descrizione
            {"data": "dataScadenza"}, // Colonna per la data di scadenza
            {
                "data": null,
                "render": function (data, type, row) {
                    //logica aggiunta button
                    return '<button class="btn btn-danger eliminaRiga" data-id="' + data.id + '"><i class="fas fa-trash"></i> Elimina</button>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;' + // Aggiungi spaziatura tra i bottoni
                        '<button class="btn btn-primary aggiungiTask" data-id="' + data.id + '"><i class="fas fa-edit"></i> Modifica</button>';


                }
            }
        ],
        "columnDefs": [
            {targets: [0], orderData: [0, 1]},
            {targets: [1], orderData: [1, 0]},
            {targets: [2], orderData: [2, 0]},
            {targets: [3], orderData: [3, 0]},
            {targets: [4], orderable: false},
        ],
        "paging": true, // paginazione
        "info": true, // informazioni sulla pagina
        "searching": true, // Abilita la barra di ricerca
        "scrollCollapse": true, // Abilita lo scrolling
        "scrollY": '70%' // Imposta l'altezza dello scrolling
    });

    $('#listaTask tbody').on('click', '.eliminaRiga', function () {

        let id = $(this).data('id');

        // Mostra una finestra di dialogo di conferma dopo che si preme il pulsante, per verificare che non sia stato premuto per sbaglio
        let conferma = confirm("Sei sicuro di voler eliminare questa riga?");

        // Se si conferma, procede con l'eliminazione, facendo una chiamata ajax alla api su controller
        if (conferma) {
            $.ajax({
                type: 'DELETE',
                url: '/api/eliminaRiga/' + id,
                success: function (response) {

                    // Mostra un messaggio di successo in caso di eliminazione
                    alert(response);

                    // Ricarica la tabella
                    location.reload();
                },
                error: function (error) {

                    console.error(error);
                }
            });
        } else {

            // altrimenti non fa nulla stampa solo su console questo errore
            console.log("Eliminazione annullata");
        }
    });

    $('#listaTask tbody').on('click', '.aggiungiTask', function () {
        let rowData = table.row($(this).closest('tr')).data();
        window.location.href = '/modificaTask/' + rowData.id; // Reindirizza alla pagina del form
    });

    // Evento click sulla cella delle colonne dalla prima alla quarta
    $('#listaTask tbody').on('click', 'td:nth-child(-n+4)', function () {

        // Ottiene i dati della riga selezionata
        let rowData = table.row($(this).closest('tr')).data();

        window.location.href = '/modificaTask/' + rowData.id;

    });

});






