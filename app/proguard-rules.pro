# Mantén las clases y métodos con anotaciones de Android
-keepattributes *Annotation*

# Mantén todas las clases en el paquete com.example.zapatos y sus subpaquetes
-keep class com.example.zapatos.** { *; }

# Mantén todas las implementaciones de interfaces
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Mantén todas las implementaciones de métodos de serialización personalizada
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Evita que ProGuard elimine clases de la librería de soporte de Android
-dontwarn android.support.**
-dontwarn androidx.**
