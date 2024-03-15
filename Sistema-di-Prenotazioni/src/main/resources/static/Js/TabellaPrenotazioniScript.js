$(document).ready(function () {

    // Inizializzazione della tabella principale
    let table = $('#tabellaPrenotazioni').DataTable({
        "ajax": {
            url: "/Prenotazioni/Visualizza tutte le Prenotazioni",
            dataSrc: ""
        },
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Italian.json"
        },
        "columns": [
            {"data": "id"},
            {"data": "nomeCliente"},
            {"data": "dataPrenotazione"},
            {"data": "tipoServizio"},
            {
                "data": null,
                "render": function (data, type, row) {
                    //logica aggiunta button
                    return '<button class="btn btn-danger eliminaRiga" data-id="' + data.id + '"><i class="fas fa-trash"></i> Elimina</button>';

                }
            }
        ],
        "columnDefs": [
            { targets: [0], orderData: [0, 1] },
            { targets: [1], orderData: [1, 0] },
            { targets: [2], orderData: [2, 0] },
            { targets: [3], orderData: [3, 0] },
            { targets: [4], orderable: false },
        ],
        "paging": true, // paginazione
        "info": true, // informazioni sulla pagina
        "searching": true, // Abilita la barra di ricerca
        "scrollCollapse": true, // Abilita lo scrolling
        "scrollY": '70%' // Imposta l'altezza dello scrolling
    });




    // Evento click button elimina
    $('#tabellaPrenotazioni tbody').on('click', '.eliminaRiga', function () {

        let id = $(this).data('id');

        console.log(id)

        // Mostra una finestra di dialogo di conferma dopo che si preme il pulsante, per verificare che non sia stato premuto per sbaglio
        let conferma = confirm("Sei sicuro di voler eliminare questa riga?");

        // Se si conferma, procede con l'eliminazione, facendo una chiamata ajax alla api su controller
        if (conferma) {
            $.ajax({
                type: 'DELETE',
                url: '/Prenotazioni/Elimina una Prenotazione/' + id,
                success: function (response) {

                    console.log(response)
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



    // Evento click sulla cella delle colonne dalla prima alla quarta
    $('#tabellaPrenotazioni tbody').on('click', 'td:nth-child(-n+4)', function () {

        // Ottiene i dati della riga selezionata
        let rowData = table.row($(this).closest('tr')).data();

        window.location.href = '/ModificaPrenotazione/' + rowData.id;

    });











});
