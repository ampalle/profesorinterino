import pandas as pd
import requests
import time

API_KEY = "AIzaSyBW1284nK-PUZ7JPYKax7BI8t6-QYcJ-Kg"  # Sustituye por tu clave de Google Maps API
INPUT_CSV = "listado_centros.csv"
OUTPUT_CSV = "centros_con_coordenadas.csv"

def geocode_address(address):
    url = f"https://maps.googleapis.com/maps/api/geocode/json?address={requests.utils.quote(address)}&key={API_KEY}"
    response = requests.get(url)
    if response.status_code != 200:
        return None, None, None
    data = response.json()
    if data['status'] == 'OK':
        location = data['results'][0]['geometry']['location']
        formatted_address = data['results'][0]['formatted_address']
        return location['lat'], location['lng'], formatted_address
    return None, None, None

def main():
    df = pd.read_csv(INPUT_CSV, sep='$')
    df.columns = df.columns.str.strip().str.upper()  # Asegurar coincidencia exacta

    latitudes = []
    longitudes = []
    direcciones_google = []

    for i, row in df.iterrows():
        direccion = f"{row['DENOMINACIONGENERICA']} {row['DENOMINACIONESPECIFICA']}, {row['DOMICILIO']}, {row['CODPOSTAL']}, {row['LOCALIDAD']}, {row['PROVINCIA']}, España"
        print(f"{i+1}/{len(df)}: {direccion}")
        lat, lng, formatted = geocode_address(direccion)
        latitudes.append(lat)
        longitudes.append(lng)
        direcciones_google.append(formatted)
        time.sleep(0.1)

    df['LATITUD'] = latitudes
    df['LONGITUD'] = longitudes
    df['DIRECCION_GOOGLE'] = direcciones_google

    df.to_csv(OUTPUT_CSV, sep='$', index=False)
    print(f"\n✅ Coordenadas guardadas en {OUTPUT_CSV} con separador $")

if __name__ == "__main__":
    main()
