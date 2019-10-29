require "rspec/core/rake_task"

namespace :spec do
  desc "Run the functional suite against the CLI"
  RSpec::Core::RakeTask.new(:functional, [] => [:set_path])
  
  task :set_path do
    project_bin_dir = File.join(File.dirname(File.expand_path(__FILE__)), '..', 'bin')
    ENV['PATH'] = project_bin_dir + ':'+ ENV['PATH']
  end
end
