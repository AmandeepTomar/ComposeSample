# Jetpack Compose

- Jetpack compose is modern UI toolkit. Jetpack Compose simplifies and accelerates UI development on
  Android with less code, powerful tools, and intuitive Kotlin APIs.
- Just install the latest android studio
- Make sure you're using Kotlin 1.6.10 in your project:

```kotlin
-android {
    defaultConfig {
        ...
        minSdkVersion 21
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose true
    }
    ...

    // Set both the Java and Kotlin compilers to target Java 8.
    compileOptions {
        sourceCompatibility JavaVersion . VERSION_1_8
                targetCompatibility JavaVersion . VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion '1.1.1'
    }
}
```

- Add Jetpack Compose toolkit dependencies

```kotlin
    implementation("androidx.compose.ui:ui:1.1.1")
// Tooling support (Previews, etc.)
implementation("androidx.compose.ui:ui-tooling:1.1.1")
// Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
implementation("androidx.compose.foundation:foundation:1.1.1")
// Material Design
implementation("androidx.compose.material:material:1.1.1")
// Material design icons
implementation("androidx.compose.material:material-icons-core:1.1.1")
implementation("androidx.compose.material:material-icons-extended:1.1.1")
// Integration with observables
implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
implementation("androidx.compose.runtime:runtime-rxjava2:1.1.1")

// UI Tests
androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")

```

- Reuse your View theme in Compose
    - If you just added Compose to your project, you might want Compose to inherit the themes
      available in the View system instead of rewriting your own Material theme in Compose from
      scratch.

    - If you're using the MDC library in your Android app, the MDC Compose Theme Adapter library
      allows you to easily re-use the color, typography and shape theming from your existing
      View-based themes, in your composables. That's achieved using the MdcTheme API.

    - If you're using AppCompat XML themes instead, use the AppCompat Compose Theme Adapter that
      contains the AppCompatTheme API.

```kotlin
    // When using a MDC theme
implementation "com.google.android.material:compose-theme-adapter:1.1.1"

// When using a AppCompat theme
implementation "com.google.accompanist:accompanist-appcompat-theme:0.16.0"
```

- As Now setup is done, Now we are going to some codes and create some UI components using compose.

##### Composable functions

- Jetpack compose build around compose function. To create a composable function, just add
  the `@Composable`annotation to the function name.
- Preview your function in Android Studio
    - The @Preview annotation lets you preview your composable functions within Android Studio
      without having to build and install the app to an Android device or emulator.
    - Add the @Preview annotation before @Composable.

##### Layouts

- you build a UI hierarchy by calling composable functions from other composable functions.
  ###### ## UI Components
    - Text : Create text view
    - Column :  lets you arrange elements vertically.
    - Row :  lets you arrange elements horizontally.
    - Image : for adding image Ui component.
    - Modifiers : To decorate or configure a composable, Compose uses modifiers.
    - Button() , Card{},FloatingActionButton(), TopAppBar()
    - BOX() : As frame Layout
    - ConstraintLayout{OutlinedAvatar(Modifier.constrainsAs(avatar){centerHorizontallyTo(parent) })}

##### Material Design

- MaterialTheme.colors
- MaterialTheme.typography
- MaterialTheme.shape
- @Preview(showBackground = true) // default theme @Preview(name = "Light Mode") // Dark Theme

##### Lists and animations

- Create a list of messages
    - LazyColumn and LazyRow. These composable render only the elements that are visible on screen,
      so they are designed to be very efficient for long lists.

- discusses a number of things to be aware of when you program in Compose:

    - Composable functions can execute in any order.
    - Composable functions can execute in parallel.
    - Recomposition skips as many composable functions and lambdas as possible.
    - Recomposition is optimistic and may be canceled.
    - A composable function might be run quite frequently, as often as every frame of an animation.
    - The following sections will cover how to build composable functions to support recomposition.
      In every case, the best practice is to keep your composable functions fast, idempotent, and
      side-effect free.
    - Composable functions can execute in any order
        - Composable functions can run in parallel
            - When a composable function is invoked, the invocation might occur on a different
              thread from the caller. That means code that modifies variables in a composable lambda
              should be avoided–both because such code is not thread-safe, and because it is an
              impermissible side-effect of the composable lambda.
            - ```kotlin
          @Composable fun ListComposable(myList: List<String>) { Row(horizontalArrangement =
          Arrangement.SpaceBetween) { Column { for (item in myList) { Text("Item: $item")
          } } Text("Count: ${myList.size}")
          } } @Composable @Deprecated("Example with bug")
          fun ListWithBug(myList: List<String>) { var items = 0

      @Composable @Deprecated("Example with bug")
      fun ListWithBug(myList: List<String>) { var items = 0

          Row(horizontalArrangement = Arrangement.SpaceBetween) {
              Column {
                  for (item in myList) {
                      Text("Item: $item")
                      items++ // Avoid! Side-effect of the column recomposing.
                  }
              }
              Text("Count: $items")
          }
      }

  ```
- Recomposition skips as much as possible

  ```kotlin

/**

* Display a list of names the user can click with a header
  */ @Composable fun NamePicker(
  header: String, names: List<String>, onNameClicked: (String) -> Unit
  ) { Column { // this will recompose when [header] changes, but not when [names] changes Text(
  header, style = MaterialTheme.typography.h5)
  Divider()

       // LazyColumn is the Compose version of a RecyclerView.
       // The lambda passed to items() is similar to a RecyclerView.ViewHolder.
       LazyColumn {
           items(names) { name ->
               // When an item's [name] updates, the adapter for that item
               // will recompose. This will not recompose when [header] changes
               NamePickerItem(name, onNameClicked)
           }
       }
  } }

/**

* Display a single name the user can click.
  */ @Composable private fun NamePickerItem(name: String, onClicked: (String) -> Unit) { Text(name,
  Modifier.clickable(onClick = { onClicked(name) }))
  }

```

- Recomposition is optimistic
    - Recomposition starts whenever Compose thinks that the parameters of a composable might have
      changed. Recomposition is optimistic, which means Compose expects to finish recomposition
      before the parameters change again.
- Composable functions might run quite frequently
    - For example, if your widget tried to read device settings, it could potentially read those
      settings hundreds of times a second, with disastrous effects on your app's performance.
    - If your composable function needs data, it should define parameters for the data. You can then
      move expensive work to another thread, outside of composition, and pass the data to Compose
      using mutableStateOf or LiveData.

###### # @Compose

- this annotation informs the Compose compiler that this function is intended to convert data into
  UI.
- Composable functions emit UI hierarchy by calling other composable functions.
- Compose functions that emit UI do not need to return anything, because they describe the desired
  screen state instead of constructing UI widgets.
- Compose apps transform data into UI by calling composable functions. If your data changes, Compose
  re-executes these functions with the new data, creating an updated UI—this is called
  recomposition. Compose also looks at what data is needed by an individual composable so that it
  only needs to recompose components whose data has changed and skip recomposing those that are not
  affected.
- `State` and `MutableState` are interfaces that hold some value and trigger UI updates (
  recompositions) whenever that value changes.`val expanded = mutableStateOf(false)`
- To preserve state across recompositions, `remember` the mutable state using
  remember. `val expanded = remember { mutableStateOf(false) }`
- `remember` is used to guard against recomposition, so the state is not reset.
- State hoisting
    - In Composable functions, state that is read or modified by multiple functions should live in a
      common ancestor—this process is called state hoisting. To hoist means to lift or elevate.
    - The source of truth belongs to whoever creates and controls that state.
    - shouldShowOnboarding is using a by keyword instead of the =. This is a property delegate that
      saves you from typing .value every
      time.`var shouldShowOnboarding by remember { mutableStateOf(true) }`
    - In Compose you don't hide UI elements. Instead, you simply don't add them to the composition,
      so they're not added to the UI tree that Compose generates.
- Persisting state
    - Our app has a problem: if you run the app on a device, click on the buttons and then you
      rotate, the onboarding screen is shown again. The remember function works only as long as the
      composable is kept in the Composition. When you rotate, the whole activity is restarted so all
      state is lost. This also happens with any configuration change and on process death.
    - Instead of using `remember` you can use `rememberSaveable`. This will save each state
      surviving configuration changes (such as rotations) and process death.

###### # Animating your list

- https://developer.android.com/jetpack/compose/animation
- For this you'll use the `animateDpAsState` composable. It returns a State object whose value will
  continuously be updated by the animation until it finishes. It takes a "target value" whose type
  is Dp.

###### # Styling and theming your app

- go to theme folder where everything is listed.

### Migration to Jetpack Compose

- No need to rewrite complete your aap in new Ui using compose. you can write new Ui using compose
  and old as it was.
- As we have some existing view in xml file so change some part of view with `ComposeView` in xml
  file it self and now we meed to code part
- Move to kotlin file and get the id of compose view its like

```kotlin
    composeView.setContent {
    // set composable view here 
    MaterialTheme {
        plantComposableFunction()
    }
}

@Compose
fun plantComposableFunction() {
    // here we write our code 
    Text()
    Button()
    Image()
} 
```

###### # ViewModels and LiveData

- Note:** If you were in a scenario where a ViewModel is not available or you wouldn't want to pass
  that dependency to the composable, you could use the viewModel** function from within the
  composable to get the instance of the ViewModel.
- Composables don't have their own ViewModel instances, the same instance is shared between the
  composables and the lifecycle owner that hosts that Compose code.

###### ### To observe LiveData from a composable, use the LiveData.observeAsState() function.

- Note: LiveData.observeAsState() starts observing the LiveData and represents its values via a
  State object. Every time there would be a new value posted into the LiveData the returned State
  will be updated causing recomposition of every State.value usage.
- Sample code

```kotlin
@Composable
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {
    // Observes values coming from the VM's LiveData<Plant> field
    val plant by plantDetailViewModel.plant.observeAsState()

    // If plant is not null, display the content
    plant?.let {
        PlantDetailContent(it)
    }
}

@Composable
fun PlantDetailContent(plant: Plant) {
    PlantName(plant.name)
}

@Preview
@Composable
private fun PlantDetailContentPreview() {
    val plant = Plant("id", "Apple", "description", 3, 30, "")
    MaterialTheme {
        PlantDetailContent(plant)
    }
}
```

###### Basic layouts in Compose

- Surface
- Row
- Column
- Text
- TextField
- LazyRow
- LazyColumn
    - We can use some compose to define the items

```kotlin
    LazyColumn {
    //LazyColumn Scope 
    item {
        Text(header)
    }

    items(data) { item ->
        Item(item)
    }
    // with index 
    itemsIndexed(data) { index, item ->
        Item(index, item)
    }
}
```

```kotlin
// paddingContent = PaddingValues(start = 24.dp, end = 24.dp) that will provide the pa dding of both side of item but no padding clip off the items.
LazyRow(paddingContent = PaddingValues(start = 24.dp, end = 24.dp)) {
    Items(data) { item ->
        Item(item)
    }
}

// to keep the remember of the state of the items when we scroll the list 
val state = rememberLazyListState()
LazyRow(
    paddingContent = PaddingValues(start = 24.dp, end = 24.dp),
    state = rememberLazyListState()
) {
    Items(data) { item ->
        Item(item)
    }
}

```

```kotlin
//  LazyVerticalGrid 
// LazyHorizonta lGrid
LazyVerticalGrids(column = GridCells.Fixed(2)) {
    items(data) { item ->
        Item(item) 
    }
}

```

- `LazyHorizontalGrid` is part of the Lazy layouts API that was released in Jetpack Compose
  1.2.0-alpha05. You'll need to use at least this version when you want to use this grid. For more
  information, check the release notes.
- BottomNavigation
    - You can use the BottomNavigation composable that's a part of the Compose Material library.
      Inside the BottomNavigation composable, you can add one or more BottomNavigationItem elements,
      that will then get styled automatically by the Material library.
    - If you're interested in Material Design and would like to learn more about how to implement a
      design system using Jetpack Compose, you can follow the Theming in Compose codelab or read the
      Theming documentation.
- Scaffold
    - Use Material's `Scaffold` composable. Scaffold gives you
      a `top-level configurable composable for apps that implement Material design`. It contains
      slots for various Material concepts, one of which is the bottom bar. In this bottom bar, you
      can place the bottom navigation composable that you created in the previous step. Implement
      the MySootheApp composable. This is the top level composable for your app, so you should:

## References

- https://developer.android.com/jetpack/compose?gclid=Cj0KCQjw_viWBhD8ARIsAH1mCd4d_VU3L1P5WlBD1FJ2Nq-WIrIHukLAxZ0M4wxLh9X-Ng1IPlML5nUaAsdfEALw_wcB&gclsrc=aw.ds
- https://developer.android.com/jetpack/compose/documentation
- https://developer.android.com/jetpack/compose/interop/adding
- https://developer.android.com/jetpack/compose/animation
- https://www.youtube.com/watch?v=0z_dwBGQQWQ
- https://medium.com/androiddevelopers/brushing-up-on-compose-text-coloring-84d7d70dd8fa






    

