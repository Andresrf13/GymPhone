CREATE TABLE usuarios (id_user INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT);
INSERT INTO usuarios (username, password) values ('andres', 'VzAKqLxRg2qDlkBstR/grg==');
INSERT INTO usuarios (username, password) values ('larissa', 'BeOCLGqmrNqRcvaSGoGnPQ==');
CREATE TABLE ejercicio (id_ejercicio INTEGER PRIMARY KEY AUTOINCREMENT, ejercicio_name TEXT, tipo TEXT, instrucciones TEXT, image TEXT);
CREATE TABLE rutinas (id_rutina INTEGER PRIMARY KEY AUTOINCREMENT, rutina_name TEXT, id_ejerc INTEGER);
CREATE TABLE lista_ejercicios (id_lista INTEGER PRIMARY KEY AUTOINCREMENT, ejercicio INTEGER, rutina INTEGER, calorias INTEGER, tiempo TEXT);