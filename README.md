# The Movie Log
An app to see information about movies and save the ones that you love the most!

<img src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/home.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/home.webp" width="250" height="480" /> <img src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/details.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/details.webp" width="250" height="480" />


## Getting Started
Before you try to run this project you should go to [TMDB API KEY](https://www.themoviedb.org/) to create your account generate you own api key.
  * Create a <b>credentials.properties</b> file and place your key inside.
  * There is one <b>credentials.properties.sample</b> as an example.

## Multi Module Project Structure
The multi module project structure its being used to de-couple the main app of its features.
Also make it easier to separate feature parts of the application from the design-system.

## Used Technologies

The code is organized using a Multi Module Project Structure with MVVM and some technologies, like:
  * <b>Koin</b> for dependency injection
  * <b>Coroutines</b> with <b>Flow</b> for async tasks
  * <b>Room</b> for local database
  * <b>Junit</b> with <b>MockK</b> for unit tests
  * <b>Espresso</b> for instrumentation tests (MovieDetailsActivityTest class)
  * <b>Retrofit</b> for REST API calls
  * <b>Navigation</b> of Jetpack Components
  * <b>CI Integration </b> with <b>GitHub Actions</b>
 
 
 <img src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/search.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/search.webp" width="250" height="480" />  <img src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/favorites.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/MovieLog/blob/master/readme-pictures/favorites.webp" width="250" height="480" />
 
## License
This project is licensed under the MIT License - see the LICENSE file for details
