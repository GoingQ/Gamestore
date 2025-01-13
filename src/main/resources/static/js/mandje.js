"use strict";
import {byId, setText} from "./util.js";

const gameObjectArray = [];
let gameIncluded = false;
const tableBody = byId("mandjeBody");

const game = JSON.parse(sessionStorage.getItem("game"));
console.log(game);
const inhoud = JSON.parse(sessionStorage.getItem("x"));
console.log(inhoud);

if (sessionStorage.getItem("mandjeContent") !== null) {
    const mandjeOldContent = sessionStorage.getItem("mandjeContent");
    document.getElementById("mandjeBody").innerHTML = mandjeOldContent;
    let titels = "";
    for (let i = 0; i < tableBody.rows.length; i++) {
        titels = tableBody.rows[i].cells[0].innerText;
        if (titels === game.titel) {
            gameIncluded = true;
            break;
        }
    }
    updateTotal();
}
if (! gameIncluded || sessionStorage.getItem("mandjeContent") === null) {
    const tr = mandjeBody.insertRow();
    tr.insertCell().innerText = game.titel;
    tr.insertCell().innerText = game.prijs;
    //used in bevestig.js
    sessionStorage.setItem("mandjeTotaal", tableBody.rows.length);
    if (inhoud !== null) {
        for (const selected of inhoud) {
            gameObjectArray.push(selected);
        }
        gameObjectArray.push(game);
    } else {
        gameObjectArray.push(game);
    }
    console.log(gameObjectArray)
    sessionStorage.setItem("x", JSON.stringify(gameObjectArray));
    updateTotal();
    updateBody();
}
function updateTotal(){
    let totaal = 0;
    for (let i = 0; i < tableBody.rows.length; i++) {
        totaal += parseFloat(tableBody.rows[i].cells[1].innerText);
    }
    setText("total", totaal);
}
function updateBody() {
    const mandjeContent = tableBody.innerHTML;
    sessionStorage.setItem("mandjeContent", mandjeContent);
}










