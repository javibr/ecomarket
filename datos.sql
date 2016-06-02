
INSERT 
INTO Tipo_producto
(nombre)
VALUES
('Leche'),
('Frutas'),
('Pescado'),
('Pizzas'),
('Vegetales'),
('Queso');

INSERT 
INTO Marcas
(nombre)
VALUES
('Pascual'),
('Ecofruit'),
('Reciomar'),
('Tarradella'),
('Popeye'),
('Ratatouille');

INSERT 
INTO Productos
(tipo_producto,denominacion,marca)
VALUES
((SELECT id FROM Tipo_producto WHERE nombre='Leche'),'Leche',(SELECT id FROM Marcas WHERE nombre='Pascual')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Melocoton',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Manzana',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Platano',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Fresas',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Sandia',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Frutas'),'Limon',(SELECT id FROM Marcas WHERE nombre='Ecofruit')),
((SELECT id FROM Tipo_producto WHERE nombre='Pescado'),'Lenguado',(SELECT id FROM Marcas WHERE nombre='Reciomar')),
((SELECT id FROM Tipo_producto WHERE nombre='Pescado'),'Langosta',(SELECT id FROM Marcas WHERE nombre='Reciomar')),
((SELECT id FROM Tipo_producto WHERE nombre='Pizzas'),'Pizza vegana',(SELECT id FROM Marcas WHERE nombre='Tarradella')),
((SELECT id FROM Tipo_producto WHERE nombre='Vegetales'),'Brocoli',(SELECT id FROM Marcas WHERE nombre='Popeye')),
((SELECT id FROM Tipo_producto WHERE nombre='Queso'),'Queso',(SELECT id FROM Marcas WHERE nombre='Ratatouille')),
((SELECT id FROM Tipo_producto WHERE nombre='Vegetales'),'Aguacate',(SELECT id FROM Marcas WHERE nombre='Popeye'));


INSERT 
INTO Presentaciones
(producto,envase,stock)
VALUES
((SELECT id FROM Productos WHERE denominacion='Leche'),'Brick',20),
((SELECT id FROM Productos WHERE denominacion='Leche'),'Paquete 6',20),
((SELECT id FROM Productos WHERE denominacion='Melocoton'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Melocoton'),'Malla',20),
((SELECT id FROM Productos WHERE denominacion='Manzana'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Manzana'),'Malla',20),
((SELECT id FROM Productos WHERE denominacion='Platano'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Platano'),'Malla',20),
((SELECT id FROM Productos WHERE denominacion='Fresas'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Fresas'),'Malla',20),
((SELECT id FROM Productos WHERE denominacion='Sandia'),'Peso',20),
((SELECT id FROM Productos WHERE denominacion='Limon'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Limon'),'Malla',20),
((SELECT id FROM Productos WHERE denominacion='Lenguado'),'Peso',20),
((SELECT id FROM Productos WHERE denominacion='Langosta'),'Peso',20),
((SELECT id FROM Productos WHERE denominacion='Pizza vegana'),'Unidad',20),
((SELECT id FROM Productos WHERE denominacion='Brocoli'),'Peso',20),
((SELECT id FROM Productos WHERE denominacion='Queso'),'Unidad',20),
((SELECT id FROM Productos WHERE denominacion='Aguacate'),'Caja',20),
((SELECT id FROM Productos WHERE denominacion='Aguacate'),'Malla',20);


INSERT 
INTO Recogida
(lugar)
VALUES
('Leganes C/Sabatini 30 913456789'),
('Getafe C/Magdalena 30 913987654'),
('Madrid C/Salamanca 30 913123789');

INSERT 
INTO Cantidades
(id_presentacion,tipo_cantidad,cantidad)
VALUES
((SELECT id FROM Presentaciones  WHERE envase='Brick' && producto='1'),'volumen','1L'),
((SELECT id FROM Presentaciones  WHERE envase='Paquete 6' && producto='1'),'unidades','6'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='2'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='2'),'peso bruto','3kg'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='3'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='3'),'peso bruto','3kg'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='4'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='4'),'peso bruto','3kg'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='5'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='5'),'peso bruto','3kg'),
((SELECT id FROM Presentaciones  WHERE envase='Peso' && producto='6'),'peso bruto','1kg'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='7'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='7'),'peso bruto','3kg'),
((SELECT id FROM Presentaciones  WHERE envase='Peso' && producto='8'),'peso neto','1kg'),
((SELECT id FROM Presentaciones  WHERE envase='Peso' && producto='9'),'peso neto','1kg'),
((SELECT id FROM Presentaciones  WHERE envase='Unidad' && producto='10'),'unidades','1'),
((SELECT id FROM Presentaciones  WHERE envase='Peso' && producto='11'),'peso neto','1kg'),
((SELECT id FROM Presentaciones  WHERE envase='Unidad' && producto='12'),'unidades','1'),
((SELECT id FROM Presentaciones  WHERE envase='Caja' && producto='13'),'peso bruto','5kg'),
((SELECT id FROM Presentaciones  WHERE envase='Malla' && producto='13'),'peso bruto','3kg');


INSERT 
INTO Precios
(id_presentacion,fecha_inicio,fecha_fin,precio)
VALUES
((SELECT id FROM Presentaciones WHERE id='1'),'2012-12-12 10:00:00','2016-12-12 10:00:00','1.5'),
((SELECT id FROM Presentaciones WHERE id='2'),'2012-12-12 10:00:00','2016-12-12 10:00:00','6'),
((SELECT id FROM Presentaciones WHERE id='3'),'2012-12-12 10:00:00','2016-12-12 10:00:00','5'),
((SELECT id FROM Presentaciones WHERE id='4'),'2012-12-12 10:00:00','2016-12-12 10:00:00','3'),
((SELECT id FROM Presentaciones WHERE id='5'),'2012-12-12 10:00:00','2016-12-12 10:00:00','6'),
((SELECT id FROM Presentaciones WHERE id='6'),'2012-12-12 10:00:00','2016-12-12 10:00:00','4'),
((SELECT id FROM Presentaciones WHERE id='7'),'2012-12-12 10:00:00','2016-12-12 10:00:00','6'),
((SELECT id FROM Presentaciones WHERE id='8'),'2012-12-12 10:00:00','2016-12-12 10:00:00','3'),
((SELECT id FROM Presentaciones WHERE id='9'),'2012-12-12 10:00:00','2016-12-12 10:00:00','8'),
((SELECT id FROM Presentaciones WHERE id='10'),'2012-12-12 10:00:00','2016-12-12 10:00:00','5'),
((SELECT id FROM Presentaciones WHERE id='11'),'2012-12-12 10:00:00','2016-12-12 10:00:00','2'),
((SELECT id FROM Presentaciones WHERE id='12'),'2012-12-12 10:00:00','2016-12-12 10:00:00','5'),
((SELECT id FROM Presentaciones WHERE id='13'),'2012-12-12 10:00:00','2016-12-12 10:00:00','3'),
((SELECT id FROM Presentaciones WHERE id='14'),'2012-12-12 10:00:00','2016-12-12 10:00:00','12'),
((SELECT id FROM Presentaciones WHERE id='15'),'2012-12-12 10:00:00','2016-12-12 10:00:00','30'),
((SELECT id FROM Presentaciones WHERE id='16'),'2012-12-12 10:00:00','2016-12-12 10:00:00','2.5'),
((SELECT id FROM Presentaciones WHERE id='17'),'2012-12-12 10:00:00','2016-12-12 10:00:00','4'),
((SELECT id FROM Presentaciones WHERE id='18'),'2012-12-12 10:00:00','2016-12-12 10:00:00','5'),
((SELECT id FROM Presentaciones WHERE id='19'),'2012-12-12 10:00:00','2016-12-12 10:00:00','6.5'),
((SELECT id FROM Presentaciones WHERE id='20'),'2012-12-12 10:00:00','2016-12-12 10:00:00','4');
