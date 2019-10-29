# Functional Suite

`functional_spec/` contains Rspec-based automated testing suite that will validate the correctness of your program for the sample input and output using ruby `PTY` (pseudo-terminal) module.

Please add as many functional specs as needed when building your solution.

We do not support Windows at this point in time. If you don't have access to an OSX or Linux machine, we recommend setting up a Linux machine you can develop against using something like [VirtualBox](https://www.virtualbox.org/) or [Docker](https://docs.docker.com/docker-for-windows/#test-your-installation).

This needs [Ruby to be installed](https://www.ruby-lang.org/en/documentation/installation/), followed by some libraries. The steps are listed below.

## Setup

First, install [Ruby](https://www.ruby-lang.org/en/documentation/installation/). Then run the following commands under the `functional_spec` dir.

```
functional_spec $ ruby -v # confirm Ruby present
ruby 2.5.1p57 (2018-03-29 revision 63029) [x86_64-darwin17]
functional_spec $ gem install bundler # install bundler to manage dependencies
Successfully installed bundler-1.16.1
Parsing documentation for bundler-1.16.1
Done installing documentation for bundler after 2 seconds
1 gem installed
functional_spec $ bundle install # install dependencies
...
...
Bundle complete! 3 Gemfile dependencies, 8 gems now installed.
Use `bundle info [gemname]` to see where a bundled gem is installed.
functional_spec $ 

```

## Usage

You can run the full suite from `parking_lot` by doing
```
parking_lot $ bin/run_functional_specs
```

You can run the full suite directly from `parking_lot/functional_spec` by doing
```
parking_lot/functional_spec $ bundle exec rake spec:functional
```

You can run a specific test from `parking_lot/functional_spec` by providing the spec file and tag  for the test. In this example we're running the `can create a parking lot` test which has tag "sample".
```
parking_lot/functional_spec $ PATH=$PATH:../bin bundle exec rspec spec/parking_lot_spec.rb --tag sample
```

## Development References

We use Ruby PTY (pseudo-terminal) module to simulate interaction with your CLI app. We utilize 1 method from PTY module and wrote 2 additional methods that you may utilize:

- `PTY.spawn` method to initialize new pseudo-terminal with `command` as an input. `command` is string of command that you want to execute on the terminal (in this case, our parking lot app).
- `run_command` method that accept `pty` and `command` as input. `pty` is an array that you get after initializing new pseudo-terminal using `PTY.spawn`, while `command` is string of command that you want to execute on your app.
- `fetch_stdout` method that accept `pty`, which gobbles up and return all buffer on `stdout`. The result of this method can be asserted using RSpec `expect` method.

More information about PTY module can be seen [here](http://ruby-doc.org/stdlib-2.5.3/libdoc/pty/rdoc/PTY.html)
