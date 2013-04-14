CREATE TABLE usuarios (id_user INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, edad INTEGER, altura REAL, peso REAL);
INSERT INTO usuarios (username, password, edad, altura, peso) values ('andres', 'VzAKqLxRg2qDlkBstR/grg==', 22, 1.82, 95.01);
INSERT INTO usuarios (username, password, edad, altura, peso) values ('larissa', 'BeOCLGqmrNqRcvaSGoGnPQ==', 20, 1.60, 42.0);
CREATE TABLE ejercicio (id_ejercicio INTEGER PRIMARY KEY AUTOINCREMENT, ejercicio_name TEXT, tipo TEXT, instrucciones TEXT, image TEXT);
CREATE TABLE rutinas (id_rutina INTEGER PRIMARY KEY AUTOINCREMENT, rutina_name TEXT, id_ejerc INTEGER);
CREATE TABLE lista_ejercicios (id_lista INTEGER PRIMARY KEY AUTOINCREMENT, ejercicio INTEGER, rutina INTEGER, calorias INTEGER, tiempo TEXT);