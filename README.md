# InsuranceCompany

InsuranceCompany to projekt napisany w języku Java, mający na celu symulacje oraz zarzadzanie firma ubezpieczeniowa. Projekt ten prezentuje podstawowe mechanizmy obslugi polis, klientow, przetwarzania roszczen oraz kalkulacji skladek.

## Spis tresci

- [Opis projektu](#opis-projektu)
- [Funkcjonalnosci](#funkcjonalnosci)
- [Wymagania](#wymagania)
- [Instalacja i uruchomienie](#instalacja-i-uruchomienie)
- [Struktura projektu](#struktura-projektu)
- [Autor](#autor)

## Opis projektu

Projekt ma charakter demonstracyjny i edukacyjny. Jego glownym celem jest przedstawienie sposobu budowy systemu zarzadzania firma ubezpieczeniowa w jezyku Java. W ramach projektu mozesz znalezc przyklady:
- Zarzadzania danymi klientow
- Obslugi polis ubezpieczeniowych
- Przetwarzania roszczen
- Kalkulacji skladek ubezpieczeniowych

## Funkcjonalnosci

### Spis treści
- [Zarządzanie użytkownikami](#zarządzanie-użytkownikami)
- [Zarządzanie klientami](#zarządzanie-klientami)
- [Zarządzanie polisami](#zarządzanie-polisami)
- [Obsługa roszczeń (Claims)](#obsługa-roszczeń-claims)
- [Obsługa płatności](#obsługa-płatności)

### Zarządzanie użytkownikami
1. **Rejestracja nowego użytkownika**
    - Tworzenie kont o różnych rolach: Klient, Pracownik, Administrator.
2. **Logowanie**
    - Weryfikacja danych logowania i przydzielenie odpowiednich uprawnień.
3. **Wyświetlanie listy użytkowników**
    - Dostępne dla uprawnionych; pokazuje e-mail i rolę.
4. **Usuwanie użytkownika**
    - Możliwość usunięcia konta na podstawie unikatowego identyfikatora (np. e-mail).

### Zarządzanie klientami
1. **Dodawanie nowego klienta**
    - Wprowadzanie podstawowych danych: imię, nazwisko, adres, PESEL, telefon, e-mail.
2. **Wyszukiwanie klientów**
    - Według różnych kryteriów.
3. **Wyświetlanie listy wszystkich klientów**
    - Prezentacja pełnego zbioru zarejestrowanych klientów.
4. **Usuwanie klienta**
    - Po weryfikacji możliwość usunięcia klienta z systemu.

### Zarządzanie polisami
1. **Wystawianie nowej polisy**
    - Generowanie numeru polisy.
    - Określanie rodzaju ubezpieczenia.
    - Ustalanie składki i sumy ubezpieczenia.
    - Zapisywanie daty wystawienia.
2. **Przechowywanie historii polis**
    - System gromadzi informacje o wszystkich polisach wraz z datą wystawienia, statusem, rodzajem itp.
3. **Wyświetlanie polis**
    - Lista wszystkich polis.
    - Polisy przypisane do konkretnego klienta.
    - Pojedyncza polisa według numeru polisy.
4. **Aktualizacja składki**
    - Automatyczne wyliczanie składki.

### Obsługa roszczeń
1. **Zgłaszanie roszczenia**
    - Generowanie numeru roszczenia.
    - Klient wybiera polisę i opisuje szkodę.
    - System zapisuje roszczenie z datą zgłoszenia, powiązaniem z klientem i polisą.
2. **Wyświetlanie roszczeń**
    - Wszystkie roszczenia w systemie.
    - Roszczenia powiązane z konkretnym PESEL.
    - Roszczenia powiązane z numerem polisy.
3. **Zarządzanie roszczeniami**
    - Zmiana statusu roszczenia.

### Obsługa płatności
1. **Przetwarzanie płatności**
    - Wybór metody płatności.
    - Walidacja kwoty.
2. **Historia płatności** 
    - Zapisywanie informacji o dacie, kwocie, metodzie płatności, numerze polisy.

## Wymagania

### Projekt zostal utworzony przy uzyciu:
- **Java:** Wersja 22 SDK.
- **IDE:** IntelliJ IDEA 2024.1.1 (Community Edition).

## Instalacja i uruchomienie

1. **Klonowanie repozytorium:**

   ```bash
   git clone https://github.com/karolek2155/InsuranceCompany.git
   ```
2. **Import projektu do IntelliJ IDEA:**

- Otworz IntelliJ IDEA.
- Wybierz opcje File > Open... i wskaz folder z pobranym projektem.
- Poczekaj na indeksowanie oraz konfiguracje projektu. 

2. **Uruchomienie:**

- Skorzystaj z przycisku Run w IntelliJ IDEA.

## Struktura projektu

### Struktura katalogów:

# InsuranceCompany

## Struktura katalogów

```bash
    InsuranceCompany/
    ├── .idea/
    ├── out/
    ├── src/
    │   └── pl/
    │       └── gornik/
    │           └── insurancecompany/
    │               ├── model/
    │               │   ├── enums/
    │               │   │   ├── AutoInsuranceType.java
    │               │   │   ├── ClaimStatus.java
    │               │   │   ├── InsuranceType.java
    │               │   │   ├── PaymentMethod.java
    │               │   │   ├── PolicyStatus.java
    │               │   │   └── PropertyInsuranceType.java
    │               │   ├── policies/
    │               │   │   ├── AutoInsurancePolicy.java
    │               │   │   ├── LifeInsurancePolicy.java
    │               │   │   ├── Policy.java
    │               │   │   └── PropertyInsurancePolicy.java
    │               │   ├── users/
    │               │   │   ├── AdminUser.java
    │               │   │   ├── ClientUser.java
    │               │   │   ├── EmployeeUser.java
    │               │   │   └── User.java
    │               ├── service/
    │               │   ├── AuthenticationService.java
    │               │   ├── ClaimReport.java
    │               │   ├── Client.java
    │               │   ├── InsuranceCompany.java
    │               │   ├── InsuranceManagement.java
    │               │   └── Payment.java
    │               ├── tools/
    │               │   ├── TestDataGenerator.java
    │               │   └── Validation.java
    │               └── Main.java
    ├── .gitignore
    ├── InsuranceCompany.iml
    └── README.md
```
### Opis pakietów
- enums – zawiera wyliczenia związane z rodzajami ubezpieczeń, metodami płatności itp.
- model – klasy reprezentujące główne modele danych (np. ogólne klasy polis).
- policies – konkretne implementacje polis (np. polisa samochodowa, życiowa, nieruchomości).
- service – logika biznesowa (np. obsługa roszczeń, zarządzanie ubezpieczeniami, uwierzytelnianie).
- users – klasy reprezentujące różne typy użytkowników (administrator, klient, pracownik).
- tools – narzędzia pomocnicze, np. do generowania danych testowych czy walidacji.

## Autor
Projekt wykonał Karol Paź.