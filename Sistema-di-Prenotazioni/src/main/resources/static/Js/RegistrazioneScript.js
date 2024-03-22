//Logica della form di registrazione
$(document).ready(function () {

    $('#btnRegistrazione').click(function (event) {

        event.preventDefault();

        // Ottieni i valori dei campi
        let usernameVal = $('#username').val();
        let passwordVal = $('#password').val();
        let nomeVal = $('#nome').val();
        let cognomeVal = $('#cognome').val();
        let ruoloVal = $('#ruolo').val();

        // Verifica se tutti i campi sono stati compilati
        if (usernameVal && passwordVal && nomeVal && cognomeVal && ruoloVal) {

            let formData = {
                username: usernameVal,
                password: passwordVal,
                nome: nomeVal,
                cognome: cognomeVal,
                ruolo: ruoloVal
            };

            $.ajax({
                type: 'POST',
                url: $('#formRegistrazione').attr('action'),
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {
                    $('#formRegistrazione').hide();
                    $('#registrazioneMessage').show();
                    console.log(response);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        } else {
            // Se almeno uno dei campi è vuoto, mostra un messaggio di avviso o fai qualche altra azione
            alert("Completa tutti i campi per completare l'inserimento!");
        }
    });
});
