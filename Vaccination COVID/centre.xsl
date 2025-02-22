<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8" indent="yes" />	
	<xsl:template match="/">
		<html>
			<head>
				<title>Centre de vaccination</title>
			</head>
			
			<body>
				<table border="0">
					<tr bgcolor="#9acd32">
						<th style="text-align:left">id</th>
						<th style="text-align:left">Nom</th>
						<th style="text-align:left">Date_d_ouverture</th>
						<th style="text-align:left">Date_de_fermeture</th>
						<th style="text-align:left">Telephone</th>
						<th style="text-align:left">Modalite</th>
						<th style="text-align:left">N_adresse</th>
						<th style="text-align:left">Ville</th>
						<th style="text-align:left">Voie</th>
						<th style="text-align:left">Code postal</th>
						<th style="text-align:left">Latitude</th>
						<th style="text-align:left">Longitude</th>
						<th style="text-align:left">Lundi</th>
						<th style="text-align:left">Mardi</th>
						<th style="text-align:left">Mercredi</th>
						<th style="text-align:left">Jeudi</th>
						<th style="text-align:left">Vendredi</th>
						<th style="text-align:left">Samedi</th>
						<th style="text-align:left">Dimanche</th>

					</tr>
					<xsl:for-each select="Centres/centre">
						<tr>
							<td>
								<xsl:value-of select="id" />
							</td>
							<td>
								<xsl:value-of select="nom" />
							</td>
							<td>
								<xsl:value-of select="date_ouverture" />
							</td>
							<td>
								<xsl:value-of select="date_fermeture" />
							</td>
							<td>
								<xsl:value-of select="telephone" />
							</td>
							<td>
								<xsl:value-of select="modalite" />
							</td>
							<td>
								<xsl:value-of select="adresse/numero" />
							</td>
							<td>
								<xsl:value-of select="adresse/ville" />
							</td>
							<td>
								<xsl:value-of select="adresse/voie" />
							</td>
							<td>
								<xsl:value-of select="adresse/code_postal" />
							</td>
							<td>
								<xsl:value-of select="latitude" />
							</td>
							<td>
								<xsl:value-of select="longitude" />
							</td>
							<td>
								<xsl:value-of select="date/lundi" />
							</td>
							<td>
								<xsl:value-of select="date/mardi" />
							</td>
							<td>
								<xsl:value-of select="date/mercredi" />
							</td>
							<td>
								<xsl:value-of select="date/jeudi" />
							</td>
							<td>
								<xsl:value-of select="date/vendredi" />
							</td>
							<td>
								<xsl:value-of select="date/samedi" />
							</td>
							<td>
								<xsl:value-of select="date/dimache" />
							</td>
						</tr>
					</xsl:for-each>
				</table>




			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

