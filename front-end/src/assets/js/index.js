function carregarNav() {
    fetch('/src/nav.html')
        .then(response => response.text())
        .then(data => {
            document.querySelector('#nav').innerHTML = data;
        })
        .catch(error => console.error('Erro ao carregar o arquivo HTML:', error));
}

function redirecionaHome() {
    window.location.href = '/src/index.html';
}

carregarNav();

/* CODIGOS COMUM */

function redirecionaWorkout() {
    window.location.href = '/src/view/workout/index-workout.html';
}