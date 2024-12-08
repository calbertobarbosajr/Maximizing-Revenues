# Maximizing Revenues

<h1 align="center">Revenue Maximization App - Android</h1>

---

<h2>Description</h2>
<p>
This app was developed based on the course <strong>"Artificial Intelligence for Business and Companies"</strong> by <strong>Jones Granatyr</strong>.
</p>
<p>
One of the course modules teaches how to maximize the revenue of an online retail business, achieving over <strong>100% return</strong> compared to a strategy that does not use artificial intelligence. For this, the <strong>Thompson Sampling</strong> algorithm, from the <strong>Reinforcement Learning</strong> area, is used.
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
In the course, the Python programming language is used. This app is a Kotlin implementation of the logic presented in the course, running directly on <strong>Android</strong>.
</p>

---

<h2>Conversion Details</h2>
<p>
The logic of <strong>Thompson Sampling</strong> and the random strategy was converted to Kotlin with the following steps:
</p>
<ol>
  <li><strong>Function conversion:</strong> The Python functions were translated into Kotlin.</li>
  <li><strong>Use of equivalent libraries:</strong> Kotlin/Android libraries were used for generating random numbers and creating charts.</li>
  <li><strong>Android UI implementation:</strong> Logic was added to display results in charts and text views.</li>
</ol>

---

<h2>How It Works</h2>
<p>The app simulates two strategies for revenue maximization:</p>
<ul>
  <li><strong>Thompson Sampling:</strong> Uses the Beta distribution to learn and improve results over time.</li>
  <li><strong>Random Strategy:</strong> Makes random decisions for comparison.</li>
</ul>
<p><strong>Result Display:</strong></p>
<ul>
  <li><strong>TextViews:</strong> Show absolute and relative returns.</li>
  <li><strong>Charts:</strong>
    <ul>
      <li><strong>Bar Chart:</strong> Displays results per round.</li>
      <li><strong>Line Chart:</strong> Displays accumulated results.</li>
    </ul>
  </li>
</ul>

---

<h2>Project Setup</h2>

<h3>1. Prerequisites</h3>
<ul>
  <li>Android Studio version <strong>2024.2.1 Ladybug</strong> or higher.</li>
  <li>A device or Android emulator with API 21 or higher.</li>
</ul>

<h3>2. Clone the Repository</h3>
<p>Clone the project to your computer:</p>
<pre><code>git clone https://github.com/calbertobarbosajr/MaximizingRevenues.git</code></pre>

<h3>3. Gradle Configuration</h3>
<h4><code>settings.gradle</code> File</h4>
<p>Add the required repositories:</p>
<pre><code>dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://maven.google.com") }
        maven { url = uri("https://jitpack.io") }
    }
}
</code></pre>

<h4><code>build.gradle</code> (App Module)</h4>
<p>Ensure the following setup is included:</p>
<pre><code>android {
    namespace = "com.calberto_barbosa_jr.maximizingrevenues"
    compileSdk = 35

    viewBinding { enable = true }
}

dependencies {
implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
}
</code></pre>

<h3>4. Build and Run</h3>
<ul>
  <li>Connect a device or start an emulator.</li>
  <li>Click <strong>Run</strong> to install and execute the app.</li>
</ul>

---

<h2>Dependencies</h2>
<p>The project uses the following dependencies:</p>
<ul>
  <li><strong>MPAndroidChart:</strong> For bar and line charts.
    <pre><code>implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")</code></pre>
  </li>
  <li><strong>Android Lifecycle:</strong> For lifecycle management and UI logic.
    <pre><code>implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")</code></pre>
  </li>
</ul>

---

<h2>License</h2>
<p>This project is licensed under the <strong>MIT License</strong>. For more details, refer to the 
<a href="https://www.mit.edu/~amini/LICENSE.md">LICENSE</a> file.</p>

---

<h2>Contact</h2>
<p>Developed by <strong>Carlos Alberto</strong>.  
For questions or suggestions, feel free to reach out: [Contact Information].</p>
