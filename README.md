<h1 align="center">Maximizing Revenue App - Android</h1>

---

<h2>Description</h2>
<p>
This application was developed based on the course <strong>"Artificial Intelligence for Business and Companies"</strong> by <strong>Jones Granatyr</strong>.
</p>
<p>
One of the modules in the course teaches how to maximize the revenue of an online retail business, achieving over <strong>100% return</strong> compared to a strategy that does not use artificial intelligence. This is done using the <strong>Thompson Sampling</strong> algorithm, which is part of the <strong>Reinforcement Learning</strong> field.
</p>
<ul>
  <li><strong>Course in Portuguese:</strong> 
    <a href="https://www.udemy.com/course/inteligencia-artificial-empresas-negocios/?couponCode=ACCAGE0923">Inteligência Artificial aplicada para Empresas e Negócios</a>
  </li>
  <li><strong>Course in English:</strong> 
    <a href="https://www.udemy.com/course/ai-for-business/?couponCode=ACCAGE0923">Artificial Intelligence for Business</a>
  </li>
</ul>
<p>
While the course uses Python, this application is a Kotlin implementation of the logic, running directly on <strong>Android</strong>.
</p>
<p>Over time, additional modifications have been made to improve its functionality.</p>

---

<h2>Application Features</h2>
<p>The program simulates the revenue maximization of an online retail business using two different strategies:</p>
<ul>
  <li><strong>Thompson Sampling:</strong> Uses Bayesian learning to improve performance over time.</li>
  <li><strong>Random Strategy:</strong> Makes random decisions for comparison purposes.</li>
</ul>
<p>The app uses <strong>Jetpack Compose</strong> for UI development and follows the <strong>MVVM architecture</strong>.</p>

---

<h3>1. Application Structure</h3>
<p>The program follows the MVVM architecture:</p>
<ul>
  <li><strong>View (UI):</strong> User interface created with Jetpack Compose.</li>
  <li><strong>ViewModel:</strong> Contains the business logic and manages simulation state.</li>
  <li><strong>Model:</strong> Holds data such as conversion rates and rewards.</li>
</ul>

<h3>2. Simulation with Strategies</h3>
<p>The app simulates the selection of actions over <strong>10,000 rounds</strong>:</p>
<ul>
  <li><strong>Random Strategy:</strong> Chooses actions randomly.</li>
  <li><strong>Thompson Sampling:</strong> Uses a Beta distribution to choose actions based on past performance.</li>
</ul>

<h3>3. Key Components</h3>
<ul>
  <li>
    <strong>SimulationViewModel:</strong> Manages simulation state and logic:
    <ul>
      <li><strong>States:</strong> Tracks progress, controls simulation execution.</li>
      <li><strong>Logic:</strong> Implements the algorithms for both strategies.</li>
    </ul>
  </li>
  <li>
    <strong>UI (Jetpack Compose):</strong> Includes:
    <ul>
      <li>A <strong>Start Simulation</strong> button.</li>
      <li>A <strong>LinearProgressIndicator</strong> to show progress.</li>
      <li>Results display with animations for a smooth user experience.</li>
      <li>Charts using <strong>MPAndroidChart</strong> to visualize results.</li>
    </ul>
  </li>
</ul>

<h3>4. Execution Flow</h3>
<ol>
  <li>User clicks "Start Simulation".</li>
  <li>The simulation runs for 10,000 rounds:
    <ul>
      <li><strong>Random Strategy:</strong> Chooses an action randomly.</li>
      <li><strong>Thompson Sampling:</strong> Uses Bayesian learning to choose actions.</li>
    </ul>
  </li>
  <li>The progress bar updates in real-time.</li>
  <li>After completion, results and graphs are displayed.</li>
</ol>

<h3>5. Key Concepts</h3>
<ul>
  <li><strong>Thompson Sampling:</strong> Balances exploration and exploitation to maximize rewards.</li>
  <li><strong>Beta Distribution:</strong> Used to model uncertainties and choose optimal actions.</li>
  <li><strong>Regret Curve:</strong> Shows the difference between the performance of an optimal strategy and the Thompson Sampling strategy.</li>
</ul>

---

<h2>Project Setup</h2>
<h3>1. Prerequisites</h3>
<ul>
  <li>Android Studio version <strong>2024.2.1 Ladybug</strong> or higher.</li>
  <li>A device or Android emulator with API 24 or higher.</li>
</ul>

<h3>2. Clone the Repository</h3>
<pre><code>git clone https://github.com/calbertobarbosajr/Maximizing-Revenues.git</code></pre>

<h3>3. Gradle Configuration</h3>
<h4><code>settings.gradle</code></h4>
<p>Add the required repositories:</p>
<pre><code>dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.google.com")
            url = uri("https://jitpack.io")
        }
    }    
}</code></pre>

<h4><code>build.gradle</code> (App Module)</h4>
<p>Include the following dependencies:</p>
<pre><code>android {
    namespace = "com.calberto_barbosa_jr.maximizingrevenues"
    compileSdk = 35
}

dependencies {
implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
}</code></pre>

<h3>4. Build and Run</h3>
<ul>
  <li>Connect a device or start an emulator.</li>
  <li>Click <strong>Run</strong> to install and execute the app.</li>
</ul>

---

<h2>License</h2>
<p>
This project is licensed under the <strong>MIT License</strong>. For more details, refer to the 
<a href="https://www.mit.edu/~amini/LICENSE.md">LICENSE</a> file.
</p>
