**Ruz** is an application for organizing events.<br />Single activity multi-module Android application with Jetpack Compose and MVVM pattern.

# Features
- **Sign Up/In**: Registration and login to the user's personal account. Only registered users can create and participate in events
- **Personal account**: Personal account, with the function of creating events with pictures
- **Home**: List of all events, with the ability to search by category and view details of the event
- **Event details**: Detailed information about the event, with a participation function for registered users
- **I am in**: Events in which the user participates

# Built With
|                   |
|:------------------|
| Kotlin            |
| Dagger Hilt       |
| Coroutines        |
| Flows             |
| Lottie Compose    |
| Coil Compose      |
| Firebase Auth     |
| Firebase Database |
| Firebase Storage  |
| Mockito           |

# Tests
- **Unit tests**<br />AccountViewModelTest.kt, EventDetailsViewModelTest.kt, HomeViewModelTest.kt, IamInViewModelTest.kt, NewEventRepositoryTest.kt<br />
- **UI test**<br />BottomNavigationBarKtTest.kt