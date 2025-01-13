"use strict";
import {byId, setText, toon, verberg, verwijderChildElementenVan} from "./util.js";
verberg("storing");
verberg("zero");
const response = await fetch("game/platformen");
if (response.ok) {
    verberg("storing");
    const platformen = await response.json();
    const ul = byId("platformen");

    for (const platform of platformen) {
        const li = document.createElement("li");
        const hyperlink = document.createElement("a");

        hyperlink.href = "#0";
        hyperlink.innerText = `${platform.naam}`;
        li.appendChild(hyperlink);
        ul.appendChild(li);

        hyperlink.onclick = function () {
            findById(`${platform.id}`, `${platform.naam}`)
        }
    }
} else {
    toon("storing");
}

async function findById(id, name) {
    const response = await fetch(`game/${id}`);
    if (response.ok) {
        verberg("storing");
        const gameIds = await response.json();
        const gameBody = byId("games");
        verwijderChildElementenVan(gameBody);

        setText("naamPlatform", name);
        const ul = byId("games");

            for (const gameId of gameIds) {
                console.log(gameId);
                const li = document.createElement("li");
                const hyperlink = document.createElement("a");

                hyperlink.href = "game.html";
                hyperlink.textContent = gameId.titel;

                li.appendChild(hyperlink);
                ul.appendChild(li);

                hyperlink.onclick = function () {
                    sessionStorage.setItem("game", JSON.stringify(gameId));
                }
            }
    } else {
        toon("zero");
    }
}

