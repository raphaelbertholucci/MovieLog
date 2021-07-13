# The Movie Log
An app to see information about movies and save the ones that you love the most!

<img src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/home.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/webp.png" width="250" height="450" /> <img src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/movie_details.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/movie_details.webp" width="250" height="450" />


## Getting Started
Before you try to run this project you should go to [TMDB API KEY](https://www.themoviedb.org/) to create your account generate you own api key.
Place the key on Constants class file into API_KEY variable, like this: API_KEY = "YOUR_KEY".

## Multi Module Project Structure
The multi module project structure its being used to de-couple the main app of its features.
Also make it easier to separate feature parts of the application from the design-system.

## Used Technologies

The code is organized using a Multi Module Project Structure with MVVM and some technologies, like:
  * <b>Koin</b> for dependency injection
  * <b>Coroutines</b> for async tasks
  * <b>Room</b> for local database
  * <b>Junit</b> with <b>MockK</b> for unit tests
  * <b>Espresso</b> for instrumentation tests
  * <b>Retrofit</b> for REST API calls
  * <b>Navigation</b> of Jetpack Components
 
 
 <img src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/upcoming.png" alt="" data-canonical-src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/upcoming.png" width="250" height="450" />  <img src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/favorites.png" alt="" data-canonical-src="https://github.com/raphaelbertholucci/TheMovieDB/blob/master/pictures/favorites.png" width="250" height="450" />
 
## License
This project is licensed under the Apache License - see the LICENSE file for details
