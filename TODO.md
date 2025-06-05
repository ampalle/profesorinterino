# TODO - Proyecto ProfesorInterino

## Datos y normalización
- [ ] Forzar que los códigos de provincia (`Provincia.id`) coincidan con los dos primeros dígitos del código postal del centro.
  - Extraer el prefijo del código postal (ej. 28001 → 28).
  - Usar este valor como ID manual para la provincia.
  - Asegurar que `@Id` en `Provincia` no tenga `@GeneratedValue`.

## Funcionalidad avanzada
- [ ] Calcular la distancia del centro educativo a una dirección dada.
  - Usar la API de Google Maps (o alternativa como OpenRouteService).
  - Obtener distancia en metros y duración estimada en coche y transporte público.
  - Guardar resultados en DTO temporal o caché.
  - Permitir ordenar los centros por:
    - Menor distancia
    - Menor tiempo en coche
    - Menor tiempo en transporte público

## Pendiente
- [ ] Añadir tu clave de API en configuración externa.
- [ ] Validar que el centro tiene dirección válida y geocodificable.
