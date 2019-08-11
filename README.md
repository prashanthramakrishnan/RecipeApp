RecipeApp
=======

**Developed by:[Prashanth Ramakrishnan](prashanth_r03@yahoo.co.in)**

**Features**
- Fetches the recipe details via the specified API endpoint and shows the recipe list in a recycler 
view, clicking on it launches the recipe details screen.
- Includes unit tests for presenters and instrumentation tests for the app flow.

Refer [here](https://gist.github.com/jemshit/767ab25a9670eb0083bafa65f8d786bb) for proguard rules.

**Note**
- Styling and animations are minimum, I used Android CardView to show the list and the details screen, everything is pretty straight-forward
- I decided not to use the Contentful SDK as learning to use the SDK was quite complex 🧐 and the documentation was not so explanatory, 
but I instead created my own model which did not take much time. ⏳
- There is no database in this application, data is shown as is from the API calls!

**Open source libaries used**
- **[Dagger2](https://github.com/google/dagger)**
- **[RxJava2](https://github.com/ReactiveX/RxJava)**
- **[RxAndroid](https://github.com/ReactiveX/RxAndroid)**
- **[Retrofit2](https://github.com/square/retrofit)**
- **[OkHttp3](https://github.com/square/okhttp)**
- **[Glide](https://github.com/bumptech/glide)**
- **[Gson](https://github.com/google/gson)**
- **[Timber](https://github.com/JakeWharton/timber)**
- **[Project Lombok](https://projectlombok.org)**
- **[ButterKnife](https://github.com/JakeWharton/butterknife)**
- **[RxPermissions](https://github.com/tbruyelle/RxPermissions)**
- **[Robotium](https://github.com/RobotiumTech/robotium)**
- **[MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)**
- **[Commons-io](https://commons.apache.org/proper/commons-io/)**
- **[MarkdownProcessor](https://github.com/yydcdut/RxMarkdown)**

### License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.